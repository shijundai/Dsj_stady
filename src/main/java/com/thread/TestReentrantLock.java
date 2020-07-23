package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/1
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@ClassName TestReentrantLock
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/1 11:05
 *@Version 1.0
 **/
public class TestReentrantLock {
    public static ReentrantLock lock = new ReentrantLock();
    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();
    private static Condition c3 = lock.newCondition();


    public static void main(String []args) throws InterruptedException {
        new Thread(()->{
            Object obj = new Object();
            System.out.println(Thread.currentThread()+"   Start");
            try {
                lock.lock();
                System.out.println(Thread.currentThread()+" Locking");
                //
                LockSupport.parkUntil(System.currentTimeMillis() + 2000);
                System.out.println(Thread.currentThread()+" Locking ....");
                //不会释放锁
                Thread.sleep(3000000);
                //释放锁
//                c1.await();
//                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread()+"   End");
        }).start();
        Thread.sleep(1);
        new Thread(()->{
            System.out.println(Thread.currentThread()+"   Start");
            try {
                Thread.sleep(10);
                lock.lock();
                System.out.println(Thread.currentThread()+" Get Lock");
                c1.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread()+"   End");
        }).start();

        Thread.sleep(1000);

    }

}
