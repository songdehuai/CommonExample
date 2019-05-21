package com.songdehuai.commonlib.net;

import com.alibaba.fastjson.JSONObject;

import androidx.annotation.NonNull;

public class Result<T> {

    private String code;
    private String message;
    private T data;


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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
