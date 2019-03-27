package com.songdehuai.commonlib.net;

import com.alibaba.fastjson.JSONObject;

import androidx.annotation.NonNull;

/**
 * 数据泛型
 *
 * @param <T>
 */
public class Result<T> {

    private String code;
    private String message;
    private T result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return result;
    }

    public void setT(T result) {
        this.result = result;
    }

    @NonNull
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
