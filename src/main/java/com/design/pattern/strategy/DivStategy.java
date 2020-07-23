package com.design.pattern.strategy;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/23
 */

/**
 *@ClassName AddStategy
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/23 17:42
 *@Version 1.0
 **/
public class DivStategy implements Strategy {
    @Override
    public int operator(int num1, int num2) {
        return num1 / num2;
    }
}
