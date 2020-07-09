package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/13
 */

/**
 *@ClassName TestVolatitle
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/13 16:36
 *@Version 1.0
 **/
public class TestVolatitle implements Runnable {

    int count = 0;
    boolean flag = true;

    @Override
    public void run() {
        System.out.println(Thread.currentThread() +  "开始运行");
        while (flag) {
            this.count ++;
        }
        /**
         * 1.HashMap的时间复杂度,底层实现
         * 2.SpringBoot怎么启一个线程
         * 3.数据库调优
         *
         */
        System.out.println(Thread.currentThread() + "结束运行");
    }

    public static void main(String []args) {
        TestVolatitle tv = new TestVolatitle();
        tv.count=0;
        new Thread(tv).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tv.flag = false;
        System.out.println("最后结果:"+tv.count);
    }
}
