package com.hxh.dn_netframework.http;

/**
 * Created by HXH at 2019/9/18
 * 处理请求
 */
public interface IHttpService {
    void setUrl(String url);

    void setRequestData(byte[] requestData);

    void execute();

    //设置两个接口之间的关系
    void setHttpCallBack(IHttpListener httpCallBack);
}
