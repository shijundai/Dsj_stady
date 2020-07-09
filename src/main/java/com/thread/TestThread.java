package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/13
 */

/**
 *@ClassName TestThread
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/13 8:37
 *@Version 1.0
 *
 * 测试volatile关键字
 *
 **/
public class TestThread implements Runnable{

    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("Start");
        while (flag) {
//            System.out.println("run");
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("End");
    }

    public static void main(String []argrs) {
        TestThread tt = new TestThread();
        Thread t = new Thread(tt);
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Entry Switch");
        tt.flag = false;
    }
}
