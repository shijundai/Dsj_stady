package com.design.pattern.strategy;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/23
 */

/**
 *@ClassName Context
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/23 17:44
 *@Version 1.0
 **/
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int exeStategy(int num1, int num2) {
        return strategy.operator(num1,num2);
    }

}
