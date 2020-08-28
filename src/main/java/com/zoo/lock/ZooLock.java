package com.zoo.lock;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/8/28
 */

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *@ClassName ZooLock
 *@Description TODO
 *@Author Administrator
 *@Date 2020/8/28 10:29
 *@Version 1.0
 **/
public class ZooLock implements AsyncCallback.StringCallback, AsyncCallback.Children2Callback, Watcher, AsyncCallback.StatCallback {

    CountDownLatch cc;
    CountDownLatch cdl;
    ZooKeeper zk;
    String threadName;
    String nodeName;

    public ZooLock(String zkAddr) {
        try {
            this.zk = new ZooKeeper(zkAddr,10000,this);
            this.cc = new CountDownLatch(1);
            this.threadName = Thread.currentThread().getName();
            cc.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    public void tryLock() {
        cdl = new CountDownLatch(1);
        zk.create("/lock",threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,this,null);
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unLock() {
        try {
            this.zk.delete(this.nodeName,-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * CreateNode CallBack
     * @param rc
     * @param path
     * @param ctx
     * @param name
     */
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if(name != null) {
            this.nodeName = name;
            System.out.println(this.threadName + " create path:"+path+"; name:" + name);
            //获取所有子节点
            this.zk.getChildren("/",null,this,null);
        } else {

        }

    }

    /**
     * getChildren CallBack
     * @param rc
     * @param path
     * @param ctx
     * @param children
     * @param stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        String currentNode = this.nodeName.substring(1, nodeName.length());
        Collections.sort(children);
        int idx = 0;
        if((idx = children.indexOf(currentNode)) == 0) {
            System.out.println("=======================================================counDown ====");
            cdl.countDown();
        } else {
            this.zk.exists("/"+children.get(idx - 1),this, this, null);
        }
    }

    /**
     * exist 回调
     * @param rc
     * @param path
     * @param ctx
     * @param stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if(stat == null) {
            this.zk.getChildren("/",null,this,null);
        }
    }

    /**
     * Watch 回调
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {
        switch (event.getState()) {
            case SyncConnected:
                System.out.println("connected zookeeper server........" + event.getType());
                cc.countDown();
                break;
        }

        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                //前一个节点删除,后一个节点重新监控
                this.zk.getChildren("/",null,this,null);
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
        }
    }
}
