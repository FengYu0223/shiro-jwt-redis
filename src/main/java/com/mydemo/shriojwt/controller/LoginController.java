package com.mydemo.shriojwt.controller;

import com.mydemo.shriojwt.constant.BusinessException;
import com.mydemo.shriojwt.constant.ResponseHelper;
import com.mydemo.shriojwt.constant.ResponseModel;
import com.mydemo.shriojwt.controller.objectview.LoginQuery;
import com.mydemo.shriojwt.controller.objectview.LoginVO;
import com.mydemo.shriojwt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: shrio-jwt
 * @description:
 * @author: Yu
 * @create: 2020-07-25 08:46
 **/
@RestController
public class LoginController {
    @Autowired
    private IUserService userService;


    @PostMapping("/login")
    public ResponseModel<LoginVO> login(@RequestBody LoginQuery loginQuery) throws BusinessException {

        return ResponseHelper.succeed(userService.getLoginUserAndMenuInfo(loginQuery));
    }



}
