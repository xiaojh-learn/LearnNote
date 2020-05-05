package com.study.lambda.syntax;

import com.study.lambda.interfaces.LambdaSingleReturnSingleParameter;

/**
 * @author XH
 * @Package com.study.lambda.syntax
 * @date 2020/2/15 15:37
 */
public class Syntax3 {
    public static void main(String[] args) {
        //方法引用
        //可以快速实现一个lambda表达式实现指向一个已经实现的方法
        //语法：方法的隶属者：：方法名

        //注意：
        //1.参数数量和类型一定要和接口中定义的方法一致
        //2.返回值的类型和接口中定义的一致

        LambdaSingleReturnSingleParameter lambda1 = a -> change(a);

        LambdaSingleReturnSingleParameter lambda2 = Syntax3::change;

    }

    private static int change(int a){
        return a * 2;
    }
}
