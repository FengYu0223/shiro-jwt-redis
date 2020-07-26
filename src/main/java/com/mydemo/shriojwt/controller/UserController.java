package com.mydemo.shriojwt.controller;


import com.mydemo.shriojwt.constant.ResponseHelper;
import com.mydemo.shriojwt.constant.ResponseModel;
import com.mydemo.shriojwt.service.IUserService;
import com.mydemo.shriojwt.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;


    /**
     * 获取当前登录用户信息
     *

     * @return
     * @throws Exception
     */
    @GetMapping("/currentUser")
    public ResponseModel<User> getUser() throws Exception {
        log.info("【获取当前登录用户信息】");
        return ResponseHelper.succeed(null);
    }


    @GetMapping(value = "/sys/systemMenu")
    @RequiresPermissions(value = {"systemMenu"})
    //拥有超级管理员或管理员角色的用户可以访问这个接口,换成角色控制权限,改变请看MyRealm.class
    //@RequiresRoles(value = {Constant.RoleType.SYS_ASMIN_ROLE,Constant.RoleType.ADMIN},logical =  Logical.OR)
    public ResponseModel<User> findOneUser( String userNo) {
        log.info("【user:list】");
        User user = userService.selectById(userNo);
        return ResponseHelper.succeed(user);
    }

    @GetMapping(value = "/user/list")
    @RequiresPermissions(value = {"user:list"})
    public ResponseModel listUser() throws Exception {
        log.info("【user:list】");

        return ResponseHelper.succeed(null);
    }

    @GetMapping(value = "/user/add")
    @RequiresPermissions(value = {"user:add"})
    public ResponseModel addUser() throws Exception {
        log.info("【user:add】");

        return ResponseHelper.succeed(null);
    }

    @GetMapping(value = "/user/edit")
    @RequiresPermissions(value = {"user:edit"})
    public ResponseModel editUser() throws Exception {
        log.info("【user:edit】");

        return ResponseHelper.succeed(null);
    }

    @DeleteMapping(value = "/user/delete")
    @RequiresPermissions(value = {"user:delete"})
    public ResponseModel deleteUser() throws Exception {
        log.info("【user:delete】");

        return ResponseHelper.succeed(null);
    }
}

