package com.songdehuai.commonlib.net;


import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

public abstract class ResultCallBack<T> extends AbsCallback<T> {

    @Override
    public T convertResponse(Response response) throws Throwable {
        T data = null;
        Type rootType = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) rootType).getActualTypeArguments()[0];
        data = JSONObject.parseObject(response.body().string(), type);
        return data;
    }

}
