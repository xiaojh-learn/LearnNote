package com.study.reflect;

/**
 * @author XH
 * @Package com.study.reflect
 * @date 2020/2/16 15:53
 */
public class Person {
    public Person() {}

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    Person(String name){
        this.name = name;
    }

    private Person(int age){
        this.age = age;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void method(String s){
        System.out.println(">>" + s + "<<");
    }
}
