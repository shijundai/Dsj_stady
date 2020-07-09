package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/28
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName TestCyclicBarrier
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/28 16:26
 *@Version 1.0
 **/
public class TestCyclicBarrier {

    //达到阈值后一起执行
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String []args) {
        for(int i=0;i<5;i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    System.out.println("=");
                    System.out.println(cyclicBarrier.getNumberWaiting() + "在等待");
                    TimeUnit.SECONDS.sleep(1);
                    cyclicBarrier.await();
                    System.out.println(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
