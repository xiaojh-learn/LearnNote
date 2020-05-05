package com.study.lambda;

/**
 * @author XH
 * @Package com.study.lambda
 * @date 2020/2/15 14:29
 */
public class Program {
    public static void main(String[] args) {
        //接口的三种实现
        //1.使用接口实现类的方式
        Comparator comparator = new MyComparator();

        //2.使用匿名内部类的方式
        Comparator comparator1 = new Comparator() {
            @Override
            public int Compare(int a, int b) {
                return a - b;
            }
        };
        System.out.printf("");

        //3.使用Lambda表达式的方式
        Comparator comparator2 = (a, b) -> (a - b);

        //接口的使用
        //1.直接使用，实例化一个接口实现类的对象，调用接口的方法，实际为实现类重写的方法
        comparator.Compare(4, 1);

        //作为参数传入使用者方法，例如ArrayList.sort(), 参数为Comparator<> ,实际传入接口实现对象

    }
}

class MyComparator implements Comparator{

    @Override
    public int Compare(int a, int b) {
        return a - b;
    }
}

interface Comparator {
    int Compare(int a, int b);
}