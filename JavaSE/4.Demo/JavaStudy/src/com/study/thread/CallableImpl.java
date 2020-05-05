package com.study.thread;

import java.util.concurrent.Callable;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/29 13:29
 */
public class CallableImpl implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
