package com.study.thread;

import java.util.concurrent.*;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/29 15:45
 */
public class ThreadPool {
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(2);
        ThreadPoolExecutor pools = new ThreadPoolExecutor(1, 2, 3, TimeUnit.SECONDS, queue);

            pools.execute(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "Thread1");
            });

        pools.execute(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "Thread2");
        });

        pools.execute(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "Thread3");
        });

        pools.execute(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "Thread4");
        });

    }
}
