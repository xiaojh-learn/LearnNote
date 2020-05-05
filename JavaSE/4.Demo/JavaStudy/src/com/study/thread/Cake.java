package com.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/28 20:51
 */
public class Cake {
    private int num = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition cadd = lock.newCondition();
    private final Condition csub = lock.newCondition();

    public void addProduct(){
        lock.lock();
        try {
            if (num >= 20){
                try {
                    cadd.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                ++num;
                System.out.println(Thread.currentThread().getName() + " Product : " + num);
                csub.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void subProduct(){
        lock.lock();
        try {
            if(num <= 0){
                try {
                    csub.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println(Thread.currentThread().getName() + " Product : " + num);
                --num;
                cadd.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
