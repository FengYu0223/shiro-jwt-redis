package com.mydemo.shriojwt.service.model;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 *用户表
 */
public class User {

    private String userNo;

    private String mobile;

    private String password;


    private List<Role> roles;

}
