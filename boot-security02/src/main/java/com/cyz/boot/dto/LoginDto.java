package com.cyz.boot.dto;

import lombok.Data;

/**
 * @Author:cyz
 * @Date:2020/5/8 20:16
 * @Description:
 */
@Data
public class LoginDto {

    private String mobile;
    private String password;
    private String dycode;
}
