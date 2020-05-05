package com.study.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author XH
 * @Package com.study.exception
 * @date 2020/2/19 14:25
 */
public class Student extends Person {

    @Override
    public void setAge(String age) throws FileNotFoundException {
        super.setAge(age);
    }
}
