package com.mydemo.shriojwt.constant;

import com.google.common.collect.Sets;
import com.mydemo.shriojwt.service.model.Menu;
import com.mydemo.shriojwt.service.model.Role;
import com.mydemo.shriojwt.service.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Constant {

    public static final int BYTE_BUFFER = 1024;

    public static Set<String>  METHOD_URL_SET = Sets.newConcurrentHashSet();

    /**
     * 用户注册默认角色
     */
    public static final int DEFAULT_REGISTER_ROLE = 5;

    public static final int BUFFER_MULTIPLE = 10;

    //验证码过期时间
    public static final Long PASS_TIME =  50000 * 60 *1000L;

    //根菜单节点
    public static final String ROOT_MENU = "0";

    //菜单类型，1：菜单  2：按钮操作
    public static final int TYPE_MENU = 1;

    //菜单类型，1：菜单  2：按钮操作
    public static final int TYPE_BUTTON = 2;

    public static Boolean isPass = false;

    //用户名登录
    public static final int LOGIN_USERNAME = 0;
    //手机登录
    public static final int LOGIN_MOBILE = 1;
    //邮箱登录
    public static final int LOGIN_EMAIL = 2;

    //启用
    public static final int ENABLE = 1;
    //禁用
    public static final int DISABLE = 0;

    public static class FilePostFix{
        public static final String ZIP_FILE =".zip";

        public static final String [] IMAGES ={"jpg", "jpeg", "JPG", "JPEG", "gif", "GIF", "bmp", "BMP", "png"};
        public static final String [] ZIP ={"ZIP","zip","rar","RAR"};
        public static final String [] VIDEO ={"mp4","MP4","mpg","mpe","mpa","m15","m1v", "mp2","rmvb"};
        public static final String [] APK ={"apk","exe"};
        public static final String [] OFFICE ={"xls","xlsx","docx","doc","ppt","pptx"};

    }
    public class FileType{
        public static final int FILE_IMG = 1;
        public static final int FILE_ZIP = 2;
        public static final int FILE_VEDIO= 3;
        public static final int FILE_APK = 4;
        public static final int FIVE_OFFICE = 5;
        public static final String FILE_IMG_DIR= "/img/";
        public static final String FILE_ZIP_DIR= "/zip/";
        public static final String FILE_VEDIO_DIR= "/video/";
        public static final String FILE_APK_DIR= "/apk/";
        public static final String FIVE_OFFICE_DIR= "/office/";
    }

    public class RoleType{
        //超级管理员
        public static final String SYS_ASMIN_ROLE= "sysadmin";
        //管理员
        public static final String ADMIN= "admin";
        //普通用户
        public static final String USER= "user";
    }


    public static Set<User> userSet = Sets.newConcurrentHashSet();

    public static Set<Role> roleSet = Sets.newConcurrentHashSet();

    public static Set<Menu> menuSet = Sets.newConcurrentHashSet();

    //模拟数据库 ：用户数据
    static {
        Menu menu1 = new Menu();
        menu1.setMenuId(1);
        menu1.setParentId(0);
        menu1.setMenuCode("menu-1");
        menu1.setCode("systemMenu");
        menu1.setName("系统管理列表");
        menu1.setUrl("/api/sys/systemMenu");

        Menu menu2 = new Menu();
        menu2.setMenuId(2);
        menu2.setParentId(1);
        menu2.setMenuCode("menu-2");
        menu2.setCode("user:list");
        menu2.setName("用户管理列表");
        menu2.setUrl("/api/user/list");

        Menu menu3 = new Menu();
        menu3.setMenuId(3);
        menu3.setParentId(1);
        menu3.setMenuCode("menu-3");
        menu3.setCode("user:add");
        menu3.setName("新增用户");
        menu3.setUrl("/api/user/add");

        Menu menu4 = new Menu();
        menu4.setMenuId(4);
        menu4.setParentId(1);
        menu4.setMenuCode("menu-4");
        menu4.setCode("user:edit");
        menu4.setName("编辑用户");
        menu4.setUrl("/api/user/edit");

        Menu menu5 = new Menu();
        menu5.setMenuId(5);
        menu5.setParentId(1);
        menu5.setMenuCode("menu-5");
        menu5.setCode("user:delete");
        menu5.setName("删除用户");
        menu5.setUrl("/api/user/delete");

        List<Menu> menus1 = new ArrayList<>();
        List<Menu> menus2 = new ArrayList<>();
        List<Menu> menus3 = new ArrayList<>();

        menus1.add(menu1);
        menus1.add(menu2);
        menus1.add(menu3);
        menus1.add(menu4);
        menus1.add(menu5);

        menus2.add(menu2);
        menus2.add(menu4);

        menus3.add(menu2);
        menus3.add(menu3);
        menus3.add(menu4);
        menus3.add(menu5);

        List<Role> roles1 = new ArrayList<>();
        List<Role> roles2 = new ArrayList<>();
        List<Role> roles3 = new ArrayList<>();
        Role role1 = new Role();
        role1.setRoleCode("role-sysadmin");
        role1.setRoleName("sysadmin");
        role1.setMenus(menus1);

        Role role2 = new Role();
        role2.setRoleCode("role-admin");
        role2.setRoleName("admin");
        role2.setMenus(menus3);

        Role role3 = new Role();
        role3.setRoleCode("role-user");
        role3.setRoleName("user");
        role3.setMenus(menus2);

        roles1.add(role1);
        roles2.add(role2);
        roles3.add(role3);

        User u = new User();
        u.setUserNo("13888888881");
        u.setMobile("13888888881");
        u.setPassword("123451");
        u.setRoles(roles1);

        User u2 = new User();
        u2.setUserNo("13888888882");
        u2.setMobile("13888888882");
        u2.setPassword("123452");
        u2.setRoles(roles2);

        User u3 = new User();
        u3.setUserNo("13888888883");
        u3.setMobile("13888888883");
        u3.setPassword("123453");
        u3.setRoles(roles3);

        userSet.add(u);
        userSet.add(u2);
        userSet.add(u3);

        roleSet.add(role1);
        roleSet.add(role2);
        roleSet.add(role3);

        menuSet.add(menu1);
        menuSet.add(menu2);
        menuSet.add(menu3);
        menuSet.add(menu4);
        menuSet.add(menu5);

    }

}
