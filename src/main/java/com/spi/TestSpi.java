package com.spi;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/27
 */

import com.spi.inter.Person;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *@ClassName TestSpi
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/27 14:51
 *@Version 1.0
 **/
public class TestSpi {

    public static void main(String []args) {
        ServiceLoader<Person> services = ServiceLoader.load(Person.class);
        Iterator<Person> iter = services.iterator();
        while (iter.hasNext()) {
            Person person = iter.next();
            person.talk();
        }
    }

}
