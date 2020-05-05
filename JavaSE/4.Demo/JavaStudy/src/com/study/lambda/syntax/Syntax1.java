package com.study.lambda.syntax;

import com.study.lambda.interfaces.*;

/**
 * @author XH
 * @Package com.study.lambda.syntax
 * @date 2020/2/15 15:05
 */
public class Syntax1 {
    public static void main(String[] args) {
        //1.Lambda表达式基础语法
        //Lambda是一个匿名函数
        //（）:参数列表
        // {}:方法体
        // ->:Lambda运算符，goes to

        //无参无返回
        LamdbaNoneReturnNoneParameter lambda1 = () -> {
            System.out.println("Hello World");
        };
        lambda1.test();

        //无返回，单参数
        LambadaNoneReturnSingleParameter lambda2 = (int a) -> {
            System.out.println(a);
        };
        lambda2.test(10);

        //无返回，多参数
        LambdaNoneReturnMultipleParameter lambda3 = (int a, int b) -> {
            System.out.println(a + b);
        };
        lambda3.test(10, 20);

        //有返回，无参数
        LambdaSingleReturnNoneParameter lambda4 = () -> {return 100;};
        int ret1 = lambda4.test();
        System.out.println(ret1);

        //有返回，单参数
        LambdaSingleReturnSingleParameter lambda5 = (int a) -> {return a * 2;};
        int ret2 = lambda5.test(10);
        System.out.println(ret2);

        //有返回，多参数
        LambdaSingleReturnMultipleParameter lambda6 = (int a, int b) -> {return (a + b);};
        int ret3 = lambda6.test(10, 20);
        System.out.println(ret3);
    }
}
