package com.logtest;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/24
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@ClassName Self4jTest
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/24 9:58
 *@Version 1.0
 **/
public class Self4jTest {

    private static final Logger logger = LoggerFactory.getLogger(Self4jTest.class);

    public static void self4jTest() {
        logger.info("self4jTest method");
    }

    public static void main(String[]args){
        self4jTest();
        Log4jTest.testLog4j();
    }

}
