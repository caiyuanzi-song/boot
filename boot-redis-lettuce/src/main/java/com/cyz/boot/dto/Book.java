package com.cyz.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:cyz
 * @Date:2020/4/23 22:30
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    public String bookNum;
    public String bookName;
}
