package com.study.Demo;

public class test {
    public static void main(String[] args) {
        byte b = (byte)300;
        System.out.println(b);

    }

    public static String getType(Object o){
        return o.getClass().getName();
    }
}
