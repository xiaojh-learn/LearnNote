package com.study.thread;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/28 16:53
 */
public class CommunicationTest {
    public static void main(String[] args) {
        Communication c = new Communication();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);

        t1.start();
        t2.start();
    }
}
