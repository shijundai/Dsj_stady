package com.zoo.conf;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/8/27
 */

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 *@ClassName MyWatcherCallBack
 *@Description TODO
 *@Author Administrator
 *@Date 2020/8/27 14:56
 *@Version 1.0
 **/
public class MyWatcherCallBack implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    ZooKeeper zk;

    MyConf myConf = new MyConf();

    CountDownLatch cdl;

    public MyWatcherCallBack(String zkAddr) {
        try {
            this.zk = new ZooKeeper(zkAddr,3000,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {
        System.out.println("watched " + event.getPath() + "; type:" + event.getType() + " ;stat:" + event.getState());
            switch (event.getType()) {
                case None:
                    break;
                case NodeCreated:
                    System.out.println(event.getPath() + "   created ");
                    this.getData(event.getPath());
                    break;
                case NodeDeleted:
                    System.out.println("delete conf " + event.getPath());
                    this.myConf.setConf(null);
                    break;
                case NodeDataChanged:
                    this.getData(event.getPath());
                    break;
                case NodeChildrenChanged:
                    break;
            }
    }

    /**
     * exist callBack
     * @param rc
     * @param path
     * @param ctx
     * @param stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        System.out.println("exists CallBack start");
        if(stat != null) {
            System.out.println("exists "+path);
            this.getData(path);
        }
    }

    /**
     * getData callBack
     * @param rc
     * @param path
     * @param ctx
     * @param data
     * @param stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println("getData CallBack start");
        if(stat != null) {
            String conf = new String(data);
            System.out.println("==== get Conf :" + conf);
            this.myConf.setConf(conf);
            cdl.countDown();
        }
    }

    public void exists(String path) {
        this.zk.exists(path,this,this,null);
        try {
            cdl = new CountDownLatch(1);
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getData(String path) {
        this.zk.getData(path,this,this,null);
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public MyConf getMyConf() {
        return myConf;
    }

    public void setMyConf(MyConf myConf) {
        this.myConf = myConf;
    }
}
