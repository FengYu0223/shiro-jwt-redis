package com.mydemo.shriojwt.service;

import com.mydemo.shriojwt.config.JWTUtil;
import com.mydemo.shriojwt.constant.BusinessException;
import com.mydemo.shriojwt.constant.CodeEnum;
import com.mydemo.shriojwt.constant.Constant;
import com.mydemo.shriojwt.controller.objectview.LoginQuery;
import com.mydemo.shriojwt.controller.objectview.LoginVO;
import com.mydemo.shriojwt.service.model.Menu;
import com.mydemo.shriojwt.service.model.Role;
import com.mydemo.shriojwt.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: shrio-jwt
 * @description:
 * @author: Yu
 * @create: 2020-07-25 11:47
 **/
@Service
@Slf4j
public class IUserService {



    public User selectById(String userNo) {
        log.info("{},{}", userNo, Constant.userSet);
        for (User user : Constant.userSet) {
            if (user.getUserNo().equals(userNo)) {
                return user;
            }
        }

        return null;
    }


    public List<Menu> findMenuByRoleCode(String roleCode) {
        log.info("{},{}", roleCode, Constant.roleSet);
        for (Role role : Constant.roleSet) {
            if (roleCode.equals(role.getRoleCode())) {
                return role.getMenus();
            }
        }
        return null;

    }


    /**
     * 模拟查询数据库，找用户和密码校验，如果不存在则抛出异常，否则校验通过生成token
     *
     * @param loginQuery
     * @return
     * @throws BusinessException
     */
    public LoginVO getLoginUserAndMenuInfo(LoginQuery loginQuery) throws BusinessException {

        User user = selectById(loginQuery.getIdentity());

        if (user == null) {
            throw new BusinessException(CodeEnum.IDENTIFICATION_ERROR.getMsg(), CodeEnum.IDENTIFICATION_ERROR.getCode());

        } else {

            LoginVO loginVO = new LoginVO();
            BeanUtils.copyProperties(user, loginVO);
            loginVO.setToken(JWTUtil.sign(user.getUserNo(), user.getPassword()));
            return loginVO;
        }
    }
}
