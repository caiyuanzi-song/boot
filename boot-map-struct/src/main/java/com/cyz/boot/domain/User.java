package com.cyz.boot.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @Author:cyz
 * @Date:2020/4/2 20:17
 * @Description:
 */
@Entity
@Data
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private Integer level;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
}
