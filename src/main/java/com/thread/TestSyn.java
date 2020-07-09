package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/13
 */

import java.util.Objects;

/**
 *
 *@ClassName TestSyn
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/13 9:10
 *@Version 1.0
 **/
public class TestSyn {

    private static Integer t = 0;

    public static String a = null;

    private Integer ic = 0;

    public static void syncInt() {
        System.out.println(Thread.currentThread().getName() + "在等待获取t的锁");
        synchronized(t) {
            System.out.println(Thread.currentThread().getName() + "获取到t的锁");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void staticTest() {
        System.out.println(Thread.currentThread().getName() + "进入方法!");
    }


    public synchronized static void test() {
        System.out.println(Thread.currentThread().getName() + "进入方法!");
    }

    public void testThis() {
        this.ic = ++this.ic;
        System.out.println(Thread.currentThread().getName() + "在等待获取this的锁:"+this.hashCode());
        synchronized (this.ic) {
            System.out.println(Thread.currentThread().getName() + "进入方法!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void staticTestnNull() {
        System.out.println(Thread.currentThread().getName() + "在等待获取"+a+"的锁:"+a.hashCode());
        synchronized (a) {
            System.out.println(Thread.currentThread().getName() + "进入方法!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSyn testSyn = (TestSyn) o;
        return Objects.equals(ic, testSyn.ic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ic);
    }

    public static void main(String[]args) {
        TestSyn ts = new TestSyn();
        new Thread(ts::testThis).start();
        syncInt();
//        for(int i=0;i < 5; i++) {
//            try {
//                Thread.sleep(100);
//                ts.ic ++;
//                new Thread(ts::testThis).start();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


    }
}
