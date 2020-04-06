package com.cyz.boot.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author:cyz
 * @Date:2020/4/6 17:31
 * @Description:
 */
@Getter
@Setter
public class RestData<T> {

    private static final String SUCCESS_message = "操作成功";
    private static final String FAIL_message = "操作失败";


    private boolean status;
    private String message;
    private T data;

    public static <T> RestData<T> build() {
        return new RestData<>();
    }

    public RestData() {}

    private RestData(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 操作是否成功
     *
     * @return true | false
     */
    public boolean isSuccess() {
        return this.status;
    }

    /**
     * 操作成功，组装返回数据
     */
    public RestData<T> success() {
        return this.success(null);
    }

    /**
     * 操作成功，组装返回数据
     */
    public RestData<T> success(String message) {
        return success(message, null);
    }

    /**
     * 操作成功，组装返回数据
     */
    public RestData<T> success(String message, T data) {
        if (message != null && !message.isEmpty()) {
            this.status = true;
            this.message = message;
            this.data = data;
            return this;
        } else {
            return success(SUCCESS_message, data);
        }
    }

    /**
     * 操作成功，组装返回数据
     */
    public RestData<T> error() {
        return this.error(null);
    }

    /**
     * 操作成功，组装返回数据
     */
    public RestData<T> error(String message) {
        return error(message, null);
    }

    /**
     * 操作成功，组装返回数据
     */
    public RestData<T> error(String message, T data) {
        if (message != null && !message.isEmpty()) {
            this.status = false;
            this.message = message;
            this.data = data;
            return this;
        } else {
            return error(FAIL_message, data);
        }
    }
}
