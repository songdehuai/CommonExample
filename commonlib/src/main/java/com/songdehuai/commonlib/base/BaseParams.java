package com.songdehuai.commonlib.base;


import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.songdehuai.commonlib.net.ResultCallBack;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * 网络请求参数
 */
public class BaseParams extends HttpParams {

    /**
     * 普通的键值对参数
     */
    public transient LinkedHashMap<String, List<String>> urlParamsMap;

    /**
     * 文件的键值对参数
     */
    public transient LinkedHashMap<String, List<FileWrapper>> fileParamsMap;


    public String toJson() {
        put(this);
        return JSONObject.toJSONString(this);
    }

    public <T> void post(String url, ResultCallBack<T> resultCallBack) {
        OkGo.<T>post(url).params(this).execute(resultCallBack);
    }

    public <T> void postJson(String url, ResultCallBack<T> resultCallBack) {
        OkGo.<T>post(url).upJson(this.toJson()).execute(resultCallBack);
    }

    public <T> void get(String url, ResultCallBack<T> resultCallBack) {
        OkGo.<T>get(url).params(this).execute(resultCallBack);
    }

    public <T> BaseParams builder(ParamsBuilder<T> paramsBuilder) {
        paramsBuilder.addParams((T) this);
        return this;
    }

}

