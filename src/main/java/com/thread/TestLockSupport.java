package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/1
 */

import java.util.concurrent.locks.LockSupport;

/**
 *@ClassName TestLockSupport
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/1 9:56
 *@Version 1.0
 **/
public class TestLockSupport {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread() + "==");
//            LockSupport.parkUntil(System.currentTimeMillis()+5000);
            LockSupport.park();
            System.out.println("获取到锁");
            System.out.println(Thread.currentThread() + " End");
        });
        t.start();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread() + "==");
//            LockSupport.parkUntil(System.currentTimeMillis()+5000);
            LockSupport.parkUntil(System.currentTimeMillis()+5000);
            System.out.println(Thread.currentThread() + " End");
        });
        t1.start();
        System.out.println(Thread.currentThread() + "   start");
        Thread.sleep(3000);
        LockSupport.unpark(t);
    }

}
