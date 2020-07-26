package com.mydemo.shriojwt.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private String roleName;

    private String roleCode;

    private List<Menu> menus;

}
