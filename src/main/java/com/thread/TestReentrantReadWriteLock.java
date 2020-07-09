package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/8
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *@ClassName TestReentrantReadWriteLock
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/8 19:02
 *@Version 1.0
 **/
public class TestReentrantReadWriteLock {
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public static void testRead() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " reader start");
        readLock.lock();
        System.out.println(threadName + " reader ing...");
        readLock.unlock();
        System.out.println(threadName + " reader end");
    }

    public static void testWrite() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " write start");
        writeLock.lock();
        System.out.println(threadName + " write ing...");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writeLock.unlock();
        System.out.println(threadName + " write end");
    }

    public static void main(String []args) {
        for(int i=0;i<5;i++) {
            new Thread(()->{
                testRead();
            }).start();

            new Thread(()->{
                testWrite();
            }).start();
        }
    }
}
