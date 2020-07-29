package com.proxy.jdk;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/29
 */

/**
 *@ClassName TestBean
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/29 9:27
 *@Version 1.0
 **/
public class TestBean implements TestInterface {
    @Override
    public void test() {
        System.out.println("Test Bean no-arg test method");
    }

    @Override
    public void test(String str) {
        System.out.println("Test Bean test("+str+") method");
    }

    @Override
    public String test(Integer i) {
        System.out.println("Test Bean String test("+i+") method");
        return String.valueOf(i);
    }
}
