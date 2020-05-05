package com.study.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/26 16:32
 */

class TicketImpl implements Runnable {
    private int ticket = 100;
    private final ReentrantLock lock = new ReentrantLock();
    public TicketImpl() {
    }

    public void sale() {
        lock.lock();
        try {
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": Ticket No:" + ticket);
                --ticket;
            }
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void run() {
        while (true){
            sale();
        }
    }
}