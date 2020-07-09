package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/1
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@ClassName TestCondition
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/1 19:25
 *@Version 1.0
 **/
public class TestCondition {
    static ReentrantLock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();

    public static void test() {
        System.out.println(Thread.currentThread() +" 进入,等待获取锁...");
        lock.lock();
        System.out.println(Thread.currentThread() +" 获取到锁 sleep...");
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread() +" weekup await....");
            c1.await();
            System.out.println(Thread.currentThread() +" END");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void test2() {
        System.out.println(Thread.currentThread() +" 进入,等待获取锁...");
        lock.lock();
        System.out.println(Thread.currentThread() +" 获取到锁 sleep...");
        c1.signalAll();
        System.out.println(Thread.currentThread() +" signalAll");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() +" .........");
        lock.unlock();
    }

    public static void main(String []args) {
        try {
            new Thread(TestCondition::test).start();
            TimeUnit.SECONDS.sleep(1);
//            new Thread(TestCondition::test2).start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
