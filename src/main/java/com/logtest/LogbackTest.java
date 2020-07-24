package com.logtest;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/24
 */

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

/**
 *@ClassName LogbackTest
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/24 11:26
 *@Version 1.0
 **/
public class LogbackTest {
    static Logger log = new LoggerContext().getLogger(LogbackTest.class);

    public static void logbackTest() {
        log.info("logbackTest method");
    }

    public static void main(String[]args) {
        logbackTest();
    }
}
