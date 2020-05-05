package com.study.lambda.syntax;

import com.study.lambda.data.Person;

/**
 * @author XH
 * @Package com.study.lambda.syntax
 * @date 2020/2/15 15:44
 */
public class Syntax4 {
    public static void main(String[] args) {
        //构造方法的引用
        PersonCreator creator = Person::new;
        Person a = creator.getPerson();

        PersonCreator2 creator2 = Person::new;
        Person b = creator2.getPerson("xiaoming", 10);

    }
}

//需求：
interface PersonCreator{
    Person getPerson();
}

interface PersonCreator2{
    Person getPerson(String name, int age);
}