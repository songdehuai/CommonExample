package com.songdehuai.commonlib.net;


import com.google.gson.Gson;

import androidx.annotation.NonNull;

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

    public T getData() {
        return result;
    }

    public void setData(T result) {
        this.result = result;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
