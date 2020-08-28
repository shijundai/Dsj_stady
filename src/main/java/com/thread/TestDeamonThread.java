package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/8/26
 */

/**
 *@ClassName TestDeamonThread
 *@Description TODO
 *@Author Administrator
 *@Date 2020/8/26 15:32
 *@Version 1.0
 **/
public class TestDeamonThread {

    public static void main(String[] args) {
        Thread deamonThread = new Thread(() -> {
            try {
                System.out.println("===== Daemon start ==========");
                Thread.sleep(10000);
                System.out.println("===== Daemon end ==========");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        deamonThread.setDaemon(true);
        deamonThread.start();
    }

}
