package com.proxy.cglib;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/29
 */

/**
 *@ClassName TestBean
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/29 14:59
 *@Version 1.0
 **/
public class TestBean {

    public void test() {
        System.out.println("Test Bean no-arg test method");
    }

    public void test(String str) {
        System.out.println("Test Bean test("+str+") method");
    }

    public String test(Integer i) {
        System.out.println("Test Bean String test("+i+") method");
        return String.valueOf(i);
    }
}
