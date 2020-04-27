package com.cyz.boot.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @Author:cyz
 * @Date:2020/4/27 21:54
 * @Description:
 */
@Service
public class SecurityService {

    @Secured("ROLE_USER")
    public String secure(){
        return "hello security";
    }

    @PreAuthorize("true")
    public String authorized(){
        return "hello Cyz";
    }

    @PreAuthorize("false")
    public String denied(){
        return "Goodbye Cyz";
    }
}
