package com.utils;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/22
 */

import java.util.*;

/**
 *@ClassName TestCollections
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/22 17:03
 *@Version 1.0
 **/
public class TestCollections {

    public static void main(String[]args) {
        Set<String> set = new HashSet<>();
        set.add("123");
        set.add("456");
        set.add("789");
        Set<String> set2 = Collections.unmodifiableSet(set);
        set2.add("000");
        System.out.println(set);
        System.out.println(set2);
    }

}
