package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/7
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试锁可重入
 *
 * 及锁漏释放
 *
 *@ClassName TestNoUnLock
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/7 18:41
 *@Version 1.0
 **/
public class TestNoUnLock {
    static Lock lock = new ReentrantLock();
    public static void test1() {
        System.out.println(Thread.currentThread()+" call Test1");
        lock.lock();
        test2();
        lock.unlock();
        System.out.println(Thread.currentThread()+" end Test1");
    }

    public static void test2() {
        System.out.println(Thread.currentThread()+" call Test2");
        lock.lock();
        System.out.println(Thread.currentThread()+" end Test2");
    }

    public static void main(String[]args) {
        for(int i=0;i<5;i++) {
            new Thread(()->test1()).start();
        }
    }
}
