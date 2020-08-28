package com.zoo;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/8/25
 */

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 *@ClassName TestZk
 *@Description TODO
 *@Author Administrator
 *@Date 2020/8/25 17:23
 *@Version 1.0
 **/
public class TestZk {

    public static void main(String []args) {
        CountDownLatch ct = new CountDownLatch(1);
        //zk是有session概念的,沒有连接池概念,
        //watch 观察,回调
        //第一类, new zk的时候传入的watch,这个watch session级别的,跟path, node没有关系
        try {
            //192.168.40.247:2181,192.168.40.247:2182,192.168.40.247:2183,192.168.40.247:2184/testConf
            ZooKeeper zk = new ZooKeeper("192.168.40.247:2181,192.168.40.247:2182,192.168.40.247:2183,192.168.40.247:2184", 1000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    Event.KeeperState stat = watchedEvent.getState();
                    System.out.println("3: "+stat);
                    ct.countDown();
                }
            });
            System.out.println("1: "+zk.getState());
            String s = zk.create("/testzk", "Hello world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            Stat stat = new Stat();
            byte[] data = zk.getData("/testzk", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("path watch" + watchedEvent.getPath() + ";" + watchedEvent.getState() + ";" + watchedEvent.getType());
                    try {
                        zk.getData("/testzk",this, new Stat());
//                        zk.getData("/testzk", true, new Stat());
                    } catch (Exception e) {}
                }
            }, stat);
            stat = zk.setData("/testzk","Change".getBytes(),stat.getVersion());

            stat = zk.setData("/testzk","Change22222".getBytes(),stat.getVersion());

            zk.setData("/testzk","33333333".getBytes(),stat.getVersion());
            ct.await();
            System.out.println("2: "+zk.getState());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

}
