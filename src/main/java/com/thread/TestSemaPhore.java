package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/28
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName TestSemaPhore
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/28 17:04
 *@Version 1.0
 **/
public class TestSemaPhore {

    /**
     * 信号量 只允许3个线程同时访问 用于限流
     */
    static Semaphore semaphore = new Semaphore(3);

    public static void main(String []args) {
        for(int i=0;i<20;i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"-"+ finalI);
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
