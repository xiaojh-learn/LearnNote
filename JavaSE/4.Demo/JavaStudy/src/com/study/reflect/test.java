package com.study.reflect;

/**
 * @author XH
 * @Package com.study.reflect
 * @date 2020/2/16 15:53
 */
public class test {
    public static void main(String[] args) throws ClassNotFoundException {
        Person p = new Person();
        //获取字节码文件对象的三种方法
        //一次程序运行过程中，同一类的字节码文件对象相同
        //1.Object.getClass()
        Class class1 = p.getClass();
        Class class2 = p.getClass();

        //2.Class.class
        Class class3 = Person.class;

        //3.Class.forName("类地址")
        Class class4 = Class.forName("com.study.reflect.Person");

    }
}
