package com.proxy.cglib;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/29
 */

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 *  cglib 动态代理
 *  两种类型的callback MethodInterceptor InvocationHandler
 *
 *  Dispatcher??
 **/
public class CglibProxy {

    /**
     *
     * @param args
     */
    public static void main(String []args) {

        TestBean testBean = getProxyBean(TestBean.class, new MyLogMethodInterceptor());
        testBean.test();


    }


    public static <T> T getProxyBean(Class<T> superClass,Callback callback) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(superClass);
        enhancer.setCallback(callback);
//        enhancer.setClassLoader();
//        enhancer.setInterfaces();
//        enhancer.setNamingPolicy();
//        enhancer.setStrategy();
        return (T) enhancer.create();
    }

    public static class MyLogMethodInterceptor implements  MethodInterceptor{
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("[log] method befor ...");
            Object res = methodProxy.invokeSuper(o, objects);
            System.out.println("[log] method after ...");
            return res;
        }
    }


    public static class MyTimeMethodInterceptor implements  MethodInterceptor{
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Long begin = System.currentTimeMillis();
            Object res = methodProxy.invokeSuper(o, objects);
            System.out.println("[log time] method exec time:"+(System.currentTimeMillis()-begin)+" ...");
            return res;
        }
    }

    public static class MyLogInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return null;
        }
    }

    public static class MyTimeInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return null;
        }
    }


}
