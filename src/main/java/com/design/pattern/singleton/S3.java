package com.design.pattern.singleton;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/9
 */

/**
 * 单例模式写法4 枚举方式
 *@ClassName S3
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/9 13:57
 *@Version 1.0
 **/
public enum  S3 {
    INSTANCE;
    public static S3 getInstance() {
        return S3.INSTANCE;
    }
    
}
