package com.logtest;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/24
 */

import org.apache.log4j.Logger;

/**
 *@ClassName Log4jTest
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/24 11:21
 *@Version 1.0
 **/
public class Log4jTest {
    static Logger log = Logger.getLogger(Log4jTest.class);

    public static void testLog4j() {
        log.info("testLog4j method");
    }

    public static void main(String []args) {
        testLog4j();
    }
}
