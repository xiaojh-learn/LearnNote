package com.study.collection;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author XH
 * @Package com.study.collection
 * @date 2020/2/26 11:15
 */
public class SetTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("abc");
        set.add("abc");
        set.add("ccc");
        set.add("aaa");
        //System.out.println(set);

        HashSet<Person> set1 = new HashSet<>();
        set1.add(new Person("xiaoming", 18));
        set1.add(new Person("xiaoming", 18));
        set1.add(new Person("xiaoming", 19));
        set1.add(new Person("xiaohong", 18));
        //System.out.println(set1);

        TreeSet<String> set2 = new TreeSet<>();
        set2.add("a");
        set2.add("c");
        set2.add("b");
        //System.out.println(set2);

        TreeSet<Person> set3 = new TreeSet<Person>((o1, o2) -> o1.getAge() - o2.getAge());
        set3.add(new Person("xiaoming", 18));
        set3.add(new Person("xiaoming", 18));
        set3.add(new Person("xiaoming", 19));
        set3.add(new Person("xiaohong", 19));
        System.out.println(set3);
    }
}
