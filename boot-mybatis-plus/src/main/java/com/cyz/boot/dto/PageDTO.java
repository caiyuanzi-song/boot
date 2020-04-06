package com.cyz.boot.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author:cyz
 * @Date:2020/4/6 15:58
 * @Description:
 */
@Getter
@Setter
public class PageDTO {
    private int pageNum = 1;
    private int pageSize = 10;
}
