package com.cyz.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author:cyz
 * @Date:2020/4/27 22:17
 * @Description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("cyz")
                .password(passwordEncoder.encode("666666"))
                .roles("user")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("user","admin");
        super.configure(auth);
    }
}
