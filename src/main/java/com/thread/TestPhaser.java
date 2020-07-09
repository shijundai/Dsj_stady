package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/28
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Phaser;

/**
 *@ClassName TestPhaser
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/28 17:12
 *@Version 1.0
 **/
public class TestPhaser {

    static class Tourism implements Runnable {

        private String name;
        private Phaser phaser;
        private Random r = new Random();

        public Tourism(String name,Phaser phaser){
            this.name=name;
            this.phaser=phaser;
        }

        private void goToPoint(String point) {
            try {
                int time = r.nextInt(10000);
                Thread.sleep((long)time);
                //到达这个移相器，等待其他人
                System.out.println("第"+phaser.getPhase()+"阶段");
                phaser.arriveAndAwaitAdvance();
                System.out.println(name+"花了"+time+"到达"+point);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void startPoint() {
            goToPoint("集合点");
        }

        public void upCar() {
            goToPoint("上车");
        }

        public void hotel() {
            goToPoint("酒店");
        }

        @Override
        public void run() {
            startPoint();
            upCar();
            hotel();
        }
    }

    static Phaser phaser = new Phaser();

    public static void main(String []args) {
        List<Tourism> tourismList = new ArrayList<>();
        Phaser phaser =  new Phaser(5);
        for(int i=0;i<5;i++) {
            Tourism t = new Tourism("小"+i,phaser);
            new Thread(t).start();
        }
    }
}
