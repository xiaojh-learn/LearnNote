package com.study.exception;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Scanner;

/**
 * @author XH
 * @Package com.study
 * @date 2020/2/19 14:01
 */
public class Person {
    private String name;
    private int age;
    public Person() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) throws FileNotFoundException {
        FileReader fileReader = new FileReader(age);

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
