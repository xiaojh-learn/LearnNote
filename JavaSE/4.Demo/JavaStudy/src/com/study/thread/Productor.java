package com.study.thread;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/28 20:49
 */
public class Productor implements Runnable {
    private Cake cake;

    public Productor(Cake cake) {
        this.cake = cake;
    }

    @Override
    public void run() {
        while(true){
            cake.addProduct();
        }
    }
}
