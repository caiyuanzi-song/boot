package com.cyz.boot;

import com.cyz.boot.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @Author:cyz
 * @Date:2020/4/27 22:02
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SecurityApplication.class)
public class SecurityTest {
    @Autowired
    private SecurityService securityService;

    private Authentication authentication;

    @Before
    public void init(){
        this.authentication = new UsernamePasswordAuthenticationToken("cyz","123456");
    }

    @After
    public void close(){
        SecurityContextHolder.clearContext();
    }

    @Test
    public void secure(){
        assertThatExceptionOfType(AuthenticationException.class)
                .isThrownBy(() -> this.securityService.secure());
    }

    @Test
    public void authenticated(){
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        assertThat("hello security").isEqualTo(this.securityService.secure());
    }

    @Test
    public void preauth(){
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        assertThat("hello Cyz").isEqualTo(this.securityService.authorized());
    }

    public void denied(){
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        assertThatExceptionOfType(AuthenticationException.class)
                .isThrownBy(() -> this.securityService.denied());
    }
}
