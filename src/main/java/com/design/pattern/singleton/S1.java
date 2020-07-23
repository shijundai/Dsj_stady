package com.design.pattern.singleton;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/9
 */

/**
 * 单例模式 写法2
 *@ClassName S1
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/9 13:49
 *@Version 1.0
 **/
public class S1 {
    private static S1 instance = null;
    private S1() {}

    /**
     * 懒加载模式
     * @return
     */
    private static S1 getInstance() {
        //第一个if 用于提高并发效率
        if(instance == null) {
            synchronized (instance) {
                //双重检测 防止多并发时new出多个对象
                if(instance == null) {
                    instance = new S1();
                }
            }
        }
        return instance;
    }
}
