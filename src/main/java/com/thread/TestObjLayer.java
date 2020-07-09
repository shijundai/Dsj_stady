package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/7
 */

import org.openjdk.jol.info.ClassLayout;

/**
 *@ClassName TestObjLayer
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/7 16:49
 *@Version 1.0
 **/
public class TestObjLayer {

    public static void main(String []args) {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        System.out.println("=================================================");

        TestBean bean = new TestBean();

        System.out.println(ClassLayout.parseInstance(bean).toPrintable());
        System.out.println("=================================================");

        bean.setCount(300);
        bean.setFlag(false);
        bean.setSb(new StringBuffer());
        bean.setStr("123123");
        bean.setTotal(200);

        new Thread(()->{
            synchronized (bean) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ClassLayout.parseInstance(bean).toPrintable());
                System.out.println("=================================================");
            }
        }).start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(ClassLayout.parseInstance(bean).toPrintable());
//        System.out.println("=================================================");
        synchronized (bean) {
            System.out.println(ClassLayout.parseInstance(bean).toPrintable());
            System.out.println("=================================================");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
