package com.songdehuai.commonlib.base;


import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.songdehuai.commonlib.net.ResultCallBack;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * 网络请求参数
 *
 * @author songdehuai
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


    /**
     * 将对象转换成jsonString
     *
     * @return jsonString
     */
    public String toJson() {
        put(this);
        return JSONObject.toJSONString(this);
    }

    /**
     * 发送Post请求，表单形式
     *
     * @param url            请求地址
     * @param resultCallBack 返回数据监听
     * @param <T>            要解析的数据类型
     */
    public <T> void post(String url, ResultCallBack<T> resultCallBack) {
        OkGo.<T>post(url).params(this).execute(resultCallBack);
    }

    /**
     * 发送Post请求，json形式
     *
     * @param url            请求地址
     * @param resultCallBack 返回数据监听
     * @param <T>            要解析的数据类型
     */
    public <T> void postJson(String url, ResultCallBack<T> resultCallBack) {
        OkGo.<T>post(url).upJson(this.toJson()).execute(resultCallBack);
    }

    /**
     * 发送Get请求
     *
     * @param url            请求地址
     * @param resultCallBack 返回数据监听
     * @param <T>            要解析的数据类型
     */
    public <T> void get(String url, ResultCallBack<T> resultCallBack) {
        OkGo.<T>get(url).params(this).execute(resultCallBack);
    }

    /**
     * 构建请求参数
     *
     * @param paramsBuilder 请求参数体
     * @param <T>           请求参数类型
     * @return 构建的参数
     */
    public <T> BaseParams builder(ParamsBuilder<T> paramsBuilder) {
        paramsBuilder.addParams((T) this);
        return this;
    }

}

