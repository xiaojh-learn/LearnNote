package com.study.thread;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/27 16:47
 */
public class TicketSub extends Thread {
    private static int ticket = 100;

    public TicketSub() {
    }

    public static synchronized void sale() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": Ticket No:" + ticket);
            --ticket;
        }
    }

    @Override
    public void run() {
        while (true){
            sale();
        }
    }
}