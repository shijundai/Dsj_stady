package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/28
 */

import java.util.concurrent.CountDownLatch;

/**
 *@ClassName TestCountDwonLatch
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/28 15:53
 *@Version 1.0
 **/
public class TestCountDwonLatch {

    static CountDownLatch downLatch = new CountDownLatch(5);

    public static void main(String []args) {
        new Thread(()->{
            while (true) {
                try {
                    downLatch.await();
                    System.out.println("=================================");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<20;i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                downLatch.countDown();
            }
        }).start();
    }
}
