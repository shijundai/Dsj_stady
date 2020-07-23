package com.design.pattern.strategy;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/23
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 策略模式 Comparator也是策略模式
 * 如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为
 *
 * 枚举为固定方式的策略
 *
 *@ClassName StrategyPatternDemo
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/23 17:45
 *@Version 1.0
 **/
public class StrategyPatternDemo {

    public static void main(String[] args) {
        Context c1 = new Context(new AddStategy());
        System.out.println(c1.exeStategy(10,3));

        Context c2 = new Context(new DivStategy());
        System.out.println(c2.exeStategy(10,3));


        Context c3= new Context(StategyEnum.sub);
        System.out.println(c3.exeStategy(10,3));

        Context c4= new Context(StategyEnum.multip);
        System.out.println(c4.exeStategy(10,3));


//        Collections.sort();
//        Arrays.sort();
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new SynchronousQueue<>(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });
    }

}
