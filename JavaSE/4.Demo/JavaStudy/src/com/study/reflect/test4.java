package com.study.reflect;

import java.lang.reflect.Field;

/**
 * @author XH
 * @Package com.study.reflect
 * @date 2020/2/16 17:13
 */
public class test4 {
    public static void main(String[] args) throws Exception {
        //1.获取字节码文件对象
        Class cp = Class.forName("com.study.reflect.Person");
        //2.获取所有成员变量
        Field name = cp.getDeclaredField("name");
        //3.设置属性值
        name.setAccessible(true);
        Object obj = cp.newInstance();
        name.set(obj, "n");
        System.out.println(obj);
    }
}
