package com.mydemo.shriojwt.config;

import com.alibaba.fastjson.JSON;
import com.mydemo.shriojwt.constant.Constant;
import com.mydemo.shriojwt.service.IUserService;
import com.mydemo.shriojwt.service.model.Menu;
import com.mydemo.shriojwt.service.model.Role;
import com.mydemo.shriojwt.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private IUserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {


        String userNo = JWTUtil.getUserNo(principals.toString());
        User user = userService.selectById(userNo);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (null != user) {


        /*
        Role role = roleService.selectOne(new EntityWrapper<Role>().eq("role_code", userToRole.getRoleCode()));
        //添加控制角色级别的权限
        Set<String> roleNameSet = new HashSet<>();
        roleNameSet.add(role.getRoleName());
        simpleAuthorizationInfo.addRoles(roleNameSet);
        */
            //控制菜单级别按钮  类中用@RequiresPermissions("user:list") 对应数据库中code字段来控制controller
            ArrayList<String> pers = new ArrayList<>();
            for (Role role : user.getRoles()) {
                List<Menu> menuList = userService.findMenuByRoleCode(role.getRoleCode());
                for (Menu per : menuList) {
                    if (per.getCode() == null || per.getCode().trim().isEmpty()) {
                        pers.add(String.valueOf(per.getCode()));
                    }
                }
            }

            Set<String> permission = new HashSet<>(pers);
            simpleAuthorizationInfo.addStringPermissions(permission);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {

        String token = (String) auth.getCredentials();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (Constant.METHOD_URL_SET.contains(request.getRequestURI())) {
            request.setAttribute("currentUser", new User());
            return new SimpleAuthenticationInfo(token, token, this.getName());
        }
        // 解密获得username，用于和数据库进行对比
        String userNo = JWTUtil.getUserNo(token);
        if (userNo == null) {
            throw new AuthenticationException("token invalid");
        }
//
//        if (userService == null) {
//            this.userService = SpringContextBeanService.getBean(IUserService.class);
//        }

        User userBean = userService.selectById(userNo);

        log.info(JSON.toJSONString(userBean));

        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        if (!JWTUtil.verify(token, userNo, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }
}
