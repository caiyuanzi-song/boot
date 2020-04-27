package com.cyz.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Author:cyz
 * @Date:2020/4/27 21:47
 * @Description:
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled =  true,prePostEnabled = true,jsr250Enabled = true)
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }
}
