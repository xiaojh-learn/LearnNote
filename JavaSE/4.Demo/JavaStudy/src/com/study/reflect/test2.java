package com.study.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author XH
 * @Package com.study.reflect
 * @date 2020/2/16 16:13
 */
public class test2 {
    public static void main(String[] args) throws Exception {
        //传统创建对象
        Person p1 = new Person();
        //用字节码文件对象创建类的对象
        //1.获取字节码文件对象
        Class cp = Class.forName("com.study.reflect.Person");
        //2.获取全部public构造器
        Constructor[] constructors = cp.getConstructors();
        //  获取全部构造器
        Constructor[] constructors1 = cp.getDeclaredConstructors();
        //  获取指定public构造器
        Constructor constructor2 = cp.getConstructor(String.class, int.class);
        //  获取指定构造器
        Constructor constructor3 = cp.getDeclaredConstructor(int.class);
        //3.指定构造器
        Constructor c = constructor3;
        //4创建对象
        // 私有构造器访问权限
        c.setAccessible(true);
        Object obj = c.newInstance(18);
        Person p2 = (Person) obj;
        System.out.println(p2);
    }
}
