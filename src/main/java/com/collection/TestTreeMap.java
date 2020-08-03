package com.collection;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/8/3
 */

import com.thread.TestBean;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * treeMap TreeSet 是有序的; 他是一个红黑树结构
 **/
public class TestTreeMap {

    public static void main(String []args) {
        Map<String,String> treeMap = new TreeMap<>();
        treeMap.put("5","5");
        treeMap.put("3","5");
        treeMap.put("4","5");
        treeMap.put("1","5");
        treeMap.put("2","5");
        treeMap.put("8","5");
        treeMap.put("7","5");
        treeMap.put("9","5");
        treeMap.put("0","5");
        treeMap.put("A","5");
        Iterator<String> itre = treeMap.keySet().iterator();
        while (itre.hasNext()) {
            System.out.println(itre.next());
        }

        TreeMap<TestBean,String> tMap = new TreeMap<>(new Comparator<TestBean>() {
            @Override
            public int compare(TestBean o1, TestBean o2) {
                return o1.getStr().compareTo(o2.getStr());
            }
        });
        TestBean a = new TestBean();
        a.setStr("1");
        TestBean b = new TestBean();
        b.setStr("2");
        tMap.put(b,"1");
        tMap.put(a,"3");
        Iterator<TestBean> itre2 = tMap.keySet().iterator();
        while (itre2.hasNext()) {
            System.out.println(itre2.next());
        }
    }

}
