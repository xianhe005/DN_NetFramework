package com.hxh.dn_netframework.http;

import java.io.InputStream;

/**
 * Created by HXH at 2019/9/18
 * 处理响应结果
 */
public interface IHttpListener {
    // 接收上一个接口的结果
    void onSuccess(InputStream inputStream);

    void onFailure();
}
