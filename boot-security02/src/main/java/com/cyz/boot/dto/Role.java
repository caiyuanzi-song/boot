package com.cyz.boot.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

/**
 * @Author:cyz
 * @Date:2020/4/27 22:40
 * @Description:
 */
@Data
@TableName("s_role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long createId;
    private LocalDateTime createTime;
    private Long updateId;
    private String code;
    private String roleName;
    private Integer state;

    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
