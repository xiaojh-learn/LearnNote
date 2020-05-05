package com.study.proxy;

/**
 * @author XH
 * @Package com.study.proxy
 * @date 2020/2/17 14:23
 */
public class AdminServiceProxy implements AdminService{
    private AdminService adminservice;
    AdminServiceProxy(AdminService adminService){
        this.adminservice = adminService;
    }

    @Override
    public void update() {
        System.out.println("判断用户是否有权限进行update操作");
        adminservice.update();
        System.out.println("记录用户执行update操作的用户信息，更改内容，时间");
    }

    @Override
    public Object find() {
        System.out.println("判断用户是否有权限进行find操作");
        System.out.println("记录用户执行find操作的用户信息，更改内容，时间");
        return adminservice.find();
    }
}
