package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/24
 */

/**
 *@ClassName TestStaticSyn
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/24 17:45
 *@Version 1.0
 **/
public class TestStaticSyn {

    public static synchronized void test() {
        System.out.println(Thread.currentThread()+"获得到锁");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        System.out.println(Thread.currentThread()+"等待锁");
        synchronized (TestStaticSyn.class) {
            System.out.println(Thread.currentThread()+"获得到锁");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String []args) {
        new Thread(()->{TestStaticSyn.test();}).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //new Thread(()->{TestStaticSyn.test2();}).start();
        test2();
    }

}
