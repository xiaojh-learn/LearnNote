package com.study.thread;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/27 16:40
 */
public class SaleTest2 {
    public static void main(String[] args) {
        TicketImpl t1 = new TicketImpl();
        Thread th1 = new Thread(t1);
        Thread th2 = new Thread(t1);
        Thread th3 = new Thread(t1);

        th1.start();
        th2.start();
        th3.start();
    }
}
