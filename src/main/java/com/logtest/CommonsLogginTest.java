package com.logtest;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/24
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *@ClassName CommonsLogginTest
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/24 12:40
 *@Version 1.0
 **/
public class CommonsLogginTest {
    /**
     * 如果只引入commons-logging默认使用的日志为jdk logger
     */
    public static Log log = LogFactory.getLog(CommonsLogginTest.class);

    public static void commonsLogginTest() {
        log.info("CommonsLogginTest method");
    }

    public static void main(String[]args){
        commonsLogginTest();
    }
}
