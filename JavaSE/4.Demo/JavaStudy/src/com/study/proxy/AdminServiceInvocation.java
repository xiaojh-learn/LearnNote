package com.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author XH
 * @Package com.study.proxy
 * @date 2020/2/17 16:11
 */
public class AdminServiceInvocation implements InvocationHandler{
    private Object target;

    public AdminServiceInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("判断用户是否有权限操作");
        Object obj = method.invoke(target, args);
        if (method.getName().equals("update")){
            System.out.println("记录用户操作信息");
        }
        return obj;
    }
}
