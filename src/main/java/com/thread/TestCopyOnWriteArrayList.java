package com.thread;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/8/19
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *@ClassName TestCopyOnWriteArrayList
 *@Description TODO
 *@Author Administrator
 *@Date 2020/8/19 14:46
 *@Version 1.0
 **/
public class TestCopyOnWriteArrayList {

    public static void main(String[]args) {
        CopyOnWriteArrayList<Test> arrayList = new CopyOnWriteArrayList<>();
//        List<Test> arrayList = new ArrayList<>();
        for(int i=0;i<2;i++) {
            new Thread(() -> {
                arrayList.add(new Test("Jack", 12));
                arrayList.add(new Test("Lucy", 15));
            }).start();
        }
        for(int i=0;i<2;i++) {
            new Thread(()->{
                System.out.println("======");
                arrayList.stream().forEach(o->System.out.println(o.getName()));
            }).start();
        }

    }

}

