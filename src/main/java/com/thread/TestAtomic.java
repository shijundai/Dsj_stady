package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/24
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@ClassName TestAtomic
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/24 11:15
 *@Version 1.0
 **/
public class TestAtomic {

    static volatile int count = 0;

//    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String []args) {
        long begin = System.currentTimeMillis();
        List<Thread> tlst = new ArrayList<>();
        for(int i=0;i<10;i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
//                    count.incrementAndGet();
                    count ++;
                }
                System.out.println(Thread.currentThread().getName());
            });
            t.start();
            tlst.add(t);
        }
        for(Thread t : tlst) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(count);
        System.out.println("耗时:" + (System.currentTimeMillis() - begin));
    }

}
