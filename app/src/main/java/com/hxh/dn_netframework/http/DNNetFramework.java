package com.hxh.dn_netframework.http;

/**
 * Created by HXH at 2019/9/18
 * 供外部调用
 */
public class DNNetFramework {

    public static<T, M> void sendJsonRequest(T requestInfo, String url, Class<M> responseClass, IDataListener<M> dataListener){
        IHttpService httpService = new JsonHttpService();
        IHttpListener httpListener = new JsonHttpListener<>(responseClass, dataListener);
        HttpTask<T> httpTask = new HttpTask<>(requestInfo, url, httpService, httpListener);
        ThreadPoolManager.getInstance().execute(httpTask);
    }
}
