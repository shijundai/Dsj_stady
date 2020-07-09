package com.design.pattern.singleton;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/9
 */

/**
 *@ClassName S2
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/9 13:53
 *@Version 1.0
 **/
public class S2 {
    private S2() {}
    private static class S2Handler {
        private static S2 instance = new S2();
    }

    public static S2 getInstance() {
        return  S2Handler.instance;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
