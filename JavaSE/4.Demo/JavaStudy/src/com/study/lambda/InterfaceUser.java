package com.study.lambda;

/**
 * @author XH
 * @Package com.study.lambda
 * @date 2020/2/15 18:11
 */
public class InterfaceUser {
    public static void main(String[] args) {
        //使用lambda表达式实现这个接口
        use((a) -> -1);
    }

    //定义一个方法，使用接口
    private static void use(SomeInterface a){
        if (a.SomeMethod(3) >0){
            System.out.println("00000");
        }
        else{
            System.out.println("11111");
        }
    }
}

interface SomeInterface {
    int SomeMethod(int a);
}



