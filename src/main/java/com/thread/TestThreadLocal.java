package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/28
 */

import java.util.concurrent.locks.LockSupport;

/**
 *@ClassName TestThreadLocal
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/28 15:54
 *@Version 1.0
 **/
public class TestThreadLocal {

    public static void main(String []args) {
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("123");
        tl.set("456789");
        System.out.println(tl.get());
        tl.remove();
        System.out.println(tl.get());

    }



}
