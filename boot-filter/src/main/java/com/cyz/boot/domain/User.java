package com.cyz.boot.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Author:cyz
 * @Date:2020/4/2 20:17
 * @Description:
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
}
