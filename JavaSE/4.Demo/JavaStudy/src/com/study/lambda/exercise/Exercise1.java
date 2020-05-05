package com.study.lambda.exercise;

import com.study.lambda.data.Person;
import java.util.Comparator;
import java.util.ArrayList;

/**
 * @author XH
 * @Package com.study.lambda.exercise
 * @date 2020/2/15 16:48
 */
public class Exercise1 {
    public static void main(String[] args) {
        //集合排序
        //ArrayList<>
        //已知在ArrayList中有若干个Person对象，将这些Person对象按照年龄进行降序排序
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("xiaoming", 10));
        list.add(new Person("xiaohong", 13));
        list.add(new Person("xiaogang", 8));

        //排序
        //Comparator comparator = new Person();

        list.sort((o1, o2) -> o2.age - o1.age);

        System.out.println(list);
    }
}
