package com.study.proxy;

/**
 * @author XH
 * @Package com.study.proxy
 * @date 2020/2/17 14:21
 */
public class AdminServiceImpl  implements AdminService{

    @Override
    public void update() {
        System.out.println("修改管理系统数据");
    }

    @Override
    public Object find() {
        System.out.println("查看管理系统数据");
        return new Object();
    }
}
