package com.study.thread;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/28 16:11
 */
public class Customer implements Runnable{
    private Cake cake;

    public Customer(Cake cake) {
        this.cake = cake;
    }

    @Override
    public void run() {
        while(true){
            cake.subProduct();
        }
    }
}
