package com.design.pattern.singleton;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/9
 */

/**
 * 单例模式 写法1
 *@ClassName S0
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/9 13:48
 *@Version 1.0
 **/
public class S0 {
    private static S0 instance = new S0();
    private S0() {}
    private static S0 getInstance() {
        return instance;
    }
}
