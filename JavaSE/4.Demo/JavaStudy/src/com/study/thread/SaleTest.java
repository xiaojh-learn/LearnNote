package com.study.thread;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/26 16:31
 */
public class SaleTest{
    public static void main(String[] args) {
        TicketSub t1 = new TicketSub();
        TicketSub t2 = new TicketSub();
        TicketSub t3 = new TicketSub();

        t1.start();
        t2.start();
        t3.start();
    }
}
