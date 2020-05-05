package com.study.thread;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/28 16:05
 */
public class Account {
    private double banlance = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public Account() {}

    public void Deposit(double m) {
        lock.lock();
        try {
            banlance += m;
            System.out.println(Thread.currentThread().getName() + " The Account banlance is:" + banlance);
        } finally {
            lock.unlock();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tackOut(double m){
        lock.lock();
            try {
                if (banlance - m < 0) {
                    System.out.println(Thread.currentThread().getName() + " The Account banlance is not enough");
                }
                else{
                    banlance -= m;
                    System.out.println(Thread.currentThread().getName() + " The Account banlance is :" + banlance);
                }
            } finally {
                lock.unlock();
            }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
