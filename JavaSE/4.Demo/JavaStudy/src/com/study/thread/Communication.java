package com.study.thread;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/28 16:49
 */
public class Communication implements Runnable {
    private int i = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this){
                notify();
                if (i <= 100){
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    ++i;
                }
                else{
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
