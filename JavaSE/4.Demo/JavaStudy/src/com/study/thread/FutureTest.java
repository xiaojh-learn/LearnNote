package com.study.thread;


import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author XH
 * @Package com.study.thread
 * @date 2020/2/29 13:25
 */
public class FutureTest {
    public static void main(String[] args) {
        Callable<Integer> cable = new CallableImpl();
        FutureTask<Integer> ft = new FutureTask<Integer>(cable);
        new Thread(ft).start();
        try {
            Integer sum = ft.get();
            System.out.println(sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
