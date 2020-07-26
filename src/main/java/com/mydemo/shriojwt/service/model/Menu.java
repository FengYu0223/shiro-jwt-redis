package com.mydemo.shriojwt.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    /**
     * 菜单代号,规范权限标识
     */
    private String menuCode;

    /**
     * 父菜单主键
     */
    private Integer parentId;


    private Integer menuId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单类型，0：菜单  `：业务按钮
     */

    private Integer menuType;
    /**
     * 菜单的序号
     */
    private Integer num;
    /**
     * 菜单地址
     */
    private String url;

    private String code;

    private String icon;


}
