package com.study.lambda.data;

import java.util.Comparator;

/**
 * @author XH
 * @Package com.study.lambda.data
 * @date 2020/2/15 15:42
 */
public class Person implements Comparator<Person> {
    public String name;
    public int age;

    public Person() {
        System.out.println("无参构造函数执行");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("有参构造函数执行");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    @Override
    public int compare(Person o1, Person o2) {
        return o2.age - o1.age;
    }


}

