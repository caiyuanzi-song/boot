package com.cyz.boot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author:cyz
 * @Date:2020/4/27 22:44
 * @Description:
 */
@Data
@TableName("s_user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long createId;
    private LocalDateTime createTime;
    private Long userId;
    private Long roleId;

    public UserRole(Long id, Long userId, Long roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }
}
