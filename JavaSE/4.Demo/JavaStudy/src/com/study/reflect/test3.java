package com.study.reflect;

import java.lang.reflect.Method;

/**
 * @author XH
 * @Package com.study.reflect
 * @date 2020/2/16 16:50
 */
public class test3 {
    public static void main(String[] args) throws Exception {
        //1.获取字节码文件对象
        Class cp = Class.forName("com.study.reflect.Person");
        //2.获取字节码文件对象中的所有public方法，包括继承的方法
        Method[] methods = cp.getMethods();
        //  获取字节码文件对象中的所有方法，不包括从继承的方法
        Method[] methods1 = cp.getDeclaredMethods();
        //  获取指定的public方法
        Method method2 = cp.getMethod("method", String.class);
        //  获取指定的所有方法
        Method method3 = cp.getDeclaredMethod("method", String.class);
        //3.使用获取的方法,Class类对象使用newInstance()方法，默认调用无参构造器对象
        method2.setAccessible(true);
        Object obj = cp.newInstance();
        Object ret = method3.invoke(obj, "3");
    }
}
