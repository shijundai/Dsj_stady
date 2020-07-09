package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/7
 */

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *@ClassName TestUnSafe
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/7 17:57
 *@Version 1.0
 **/
public class TestUnSafe {

    volatile int state = 0;
    volatile String str = null;

    private static Unsafe unsafe = null;
    private static long stateOffset = 0;
    private static long strOffset = 0;

    private static int threadCount = 10000;
    private static CountDownLatch cdl = new CountDownLatch(threadCount);
    static {
        try {
            //Unsafe.getUnsafe()做了限制 不让获取
            Field filed = Unsafe.class.getDeclaredField("theUnsafe");
            filed.setAccessible(true);
            unsafe = (Unsafe) filed.get(null);
            stateOffset = unsafe.objectFieldOffset
                    (TestUnSafe .class.getDeclaredField("state"));
            System.out.println("stateOffset:"+stateOffset);

            strOffset = unsafe.objectFieldOffset
                    (TestUnSafe .class.getDeclaredField("str"));
            System.out.println("strOffset:"+strOffset);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static void main(String []args) {
        TestUnSafe t = new TestUnSafe();
        for(int i=0;i<threadCount;i++) {
            new Thread(()->{
                while(true) {
                    int now = t.state;
                    //对象,修改值的偏移量,原来值,修改值
                    if(unsafe.compareAndSwapInt(t,stateOffset,now,now+1)) {
                        cdl.countDown();
                        return;
                    } else {
                        System.out.println(Thread.currentThread().getName()+" 自旋");
                    }
                }
            }).start();
        }
        String str = t.str;
        unsafe.compareAndSwapObject(t,strOffset,str,"after");
        try {
            cdl.await();
            System.out.println("end state:" + t.state);
            System.out.println("end str:" + t.str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
