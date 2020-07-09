package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/28
 */

import java.util.concurrent.Exchanger;

/**
 *@ClassName TestExChanger
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/28 16:58
 *@Version 1.0
 **/
public class TestExChanger {
    //线程间交换信息
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String []args) {
        new Thread(()->{
            try {
                String v = exchanger.exchange("-123");
                System.out.println(Thread.currentThread().getName()+v);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程1").start();

        new Thread(()->{
            try {
                String v = exchanger.exchange("-456");
                System.out.println(Thread.currentThread().getName()+v);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程2").start();
    }
}
