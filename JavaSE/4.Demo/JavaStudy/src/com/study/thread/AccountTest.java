package com.study.thread;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/28 21:20
 */

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.Deposit(100);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.tackOut(100);
            }
        });

        t1.start();
        t2.start();
    }
}
