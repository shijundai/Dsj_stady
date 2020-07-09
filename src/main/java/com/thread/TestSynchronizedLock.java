package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/1
 */

import java.util.concurrent.locks.ReentrantLock;

/**
 *@ClassName TestSynchronizedLock
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/1 16:24
 *@Version 1.0
 **/
public class TestSynchronizedLock {

    static Object obj = new Object();



    public static void test() {
        System.out.println(Thread.currentThread() + " 进入方法,等待获取锁");
        synchronized (obj) {
            try {
                System.out.println(Thread.currentThread() + " 进入方法,获取到锁 休眠");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread() + " 休眠结束 wait");
                obj.wait();
                System.out.println(Thread.currentThread() + " wait结束 需要重新抢占锁");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread() + " 执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void noti() {
        synchronized (obj) {
            obj.notifyAll();
            try {
                System.out.println("notifyAll");
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String []args) {
        new Thread(TestSynchronizedLock::test,"线程11111").start();
        new Thread(TestSynchronizedLock::test,"线程22222").start();
        try {
            Thread.sleep(8000);
            noti();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
