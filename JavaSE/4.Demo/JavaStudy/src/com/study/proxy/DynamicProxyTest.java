package com.study.proxy;

import java.lang.reflect.Proxy;

/**
 * @author XH
 * @Package com.study.proxy
 * @date 2020/2/17 16:20
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        //创建目标对象
        AdminService adminService = new AdminServiceImpl();
        //创建目标对象的方法增强对象
        AdminServiceInvocation adminServiceInvocation = new AdminServiceInvocation(adminService);
        //使用Proxy类生成动态代理对象
        Object obj = Proxy.newProxyInstance(adminService.getClass().getClassLoader(), adminService.getClass().getInterfaces(), adminServiceInvocation);
        //调用增强后的方法
        AdminService obj1 = (AdminService) obj;
        obj1.find();
    }
}
