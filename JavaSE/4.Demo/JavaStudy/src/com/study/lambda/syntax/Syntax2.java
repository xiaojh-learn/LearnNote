package com.study.lambda.syntax;

import com.study.lambda.interfaces.*;

/**
 * @author XH
 * @Package com.study.lambda.syntax
 * @date 2020/2/15 15:27
 */
public class Syntax2 {
    public static void main(String[] args) {
        //语法精简
        //1.省略所有参数类型修饰词
        LambdaNoneReturnMultipleParameter lambda1 = (a,b) -> {
            System.out.println(a + b);
        };

        //2.如只有一个参数，省略小括号
        LambdaSingleReturnSingleParameter lambda2 = a -> {
            return a * 2;
        };

        //3.方法只有一条语句，省略大括号
        LambdaNoneReturnMultipleParameter lambda3 = (a, b) -> System.out.println(a + b);

        //4.方法只有一条返回语句，省略大括号，return
        LambdaSingleReturnSingleParameter lambda4 = a ->a * 2;
    }
}
