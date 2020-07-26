package com.mydemo.shriojwt.controller.objectview;

import com.mydemo.shriojwt.service.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: shrio-jwt
 * @description:
 * @author: Yu
 * @create: 2020-07-25 20:44
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginVO {

    private String userNo;

    private String mobile;

    private String token;

    private List<Role> roles;
}
