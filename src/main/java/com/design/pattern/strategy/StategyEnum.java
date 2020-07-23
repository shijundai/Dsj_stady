package com.design.pattern.strategy;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/23
 */

/**
 *@ClassName StategyEnum
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/23 17:49
 *@Version 1.0
 **/
public enum StategyEnum implements Strategy {

    sub {
        @Override
        public int operator(int num1, int num2) {
            return num1 - num2;
        }
    },multip {
        @Override
        public int operator(int num1, int num2) {
            return num1 * num2;
        }
    };


}
