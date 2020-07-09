package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/28
 */

import java.util.concurrent.FutureTask;

/**
 *@ClassName TestCallabel
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/28 14:59
 *@Version 1.0
 **/
public class TestCallabel {

    public static void main(String []args) {
        long begin = System.currentTimeMillis();
        FutureTask<String> t = new FutureTask(()->{
            System.out.println("123456789");
            Thread.sleep(5000);
            return "123456";
        });

        Thread th = new Thread(t);
        th.start();
//        try {
            System.out.println("等待结果");
            while(System.currentTimeMillis() - begin < 1000) {

            }
            t.cancel(true);
            System.out.println("结果"+t.isCancelled());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

}
