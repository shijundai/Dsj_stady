package com.proxy.jdk;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/29
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *@ClassName JDKProxy
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/29 9:26
 *@Version 1.0
 **/
public class JDKProxy {

    public static void main(String []args) {
        //保存动态代理生成的类的class文件
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
//        System.setProperty("")
        TestInterface proxyBean = getProxy(TestInterface.class, new LogInvocationHandler(new TestBean()));
        proxyBean = getProxy(TestInterface.class, new TimeInvocationHandler(proxyBean));
        proxyBean.test();
        System.out.println("=======================");
        proxyBean.test("dsj");
    }

    public static <I> I getProxy(Class<I> type, InvocationHandler invocationHandler) {
        return (I)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{type}, invocationHandler);
    }

    /**
     * 日志handler
     */
    public static class LogInvocationHandler implements InvocationHandler {
        Object targer;

        public LogInvocationHandler(Object targer) {
            this.targer = targer;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("[log] method befor ........");
            Object o = null;
            try {
                o = method.invoke(targer, args);
            } catch (Exception e) {
                System.out.println("[log] method exception ........");
            } finally {
                System.out.println("[log] method after");
            }
            return o;
        }
    }

    public static class TimeInvocationHandler implements InvocationHandler {
        Object targer;

        public TimeInvocationHandler(Object targer) {
            this.targer = targer;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.currentTimeMillis();
            Object o = null;
            o = method.invoke(targer, args);
            System.out.println("method exec time: "+(System.currentTimeMillis()-start));
            return o;
        }
    }

}
