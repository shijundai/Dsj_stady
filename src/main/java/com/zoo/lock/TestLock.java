package com.zoo.lock;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/8/28
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 *@ClassName TestLock
 *@Description TODO
 *@Author Administrator
 *@Date 2020/8/28 10:31
 *@Version 1.0
 **/
public class TestLock {

    public static String zkAddr = "192.168.40.247:2181,192.168.40.247:2182,192.168.40.247:2183,192.168.40.247:2184/myLock";

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger();
        for(int i=0;i<10;i++) {
            new Thread(()->{
                ZooLock zooLock = new ZooLock(zkAddr);
                zooLock.tryLock();

                System.out.println(Thread.currentThread().getName()+" working ......");
                System.out.println(Thread.currentThread().getName()+"  count : "+count.incrementAndGet());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                zooLock.unLock();
            }).start();
        }
    }

}
