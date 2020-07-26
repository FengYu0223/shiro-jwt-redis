package com.mydemo.shriojwt.controller.objectview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: shrio-jwt
 * @description:
 * @author: Yu
 * @create: 2020-07-25 20:41
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginQuery {

    private String identity;

    private String password;
}
