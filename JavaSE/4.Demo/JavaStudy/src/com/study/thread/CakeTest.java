package com.study.thread;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/28 21:01
 */

public class CakeTest {
    public static void main(String[] args) {
        Cake cake = new Cake();

        Thread t1 = new Thread(new Productor(cake));
        Thread t2 = new Thread(new Customer(cake));
        Thread t3 = new Thread(new Customer(cake));

        t1.setName("Productor");
        t2.setName("Customer1");
        t3.setName("Customer2");

        t1.start();
        t2.start();
        t3.start();

    }
}
