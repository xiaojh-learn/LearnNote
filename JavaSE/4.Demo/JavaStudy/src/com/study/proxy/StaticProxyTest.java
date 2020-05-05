package com.study.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * @author XH
 * @Package com.study.proxy
 * @date 2020/2/17 14:28
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        AdminService adminService = new AdminServiceImpl();
        AdminServiceProxy proxy = new AdminServiceProxy(adminService);
        proxy.update();
        System.out.println("---------------------------");
        proxy.find();

    }
}
