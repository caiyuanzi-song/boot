package com.cyz.boot.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author:cyz
 * @Date:2020/4/14 21:02
 * @Description:
 */
@Setter
@Getter
public class RetryData {
    private Integer retry;
    private Integer id;

    public RetryData() {
    }

    public RetryData(Integer retry, Integer id) {
        this.retry = retry;
        this.id = id;
    }
}
