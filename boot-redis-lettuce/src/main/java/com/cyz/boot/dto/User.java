package com.cyz.boot.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Author:cyz
 * @Date:2020/4/26 20:36
 * @Description:
 */
@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private Long level;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
}
