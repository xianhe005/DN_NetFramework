package com.hxh.dn_netframework.http;

/**
 * Created by HXH at 2019/9/18
 * 用于把结果回调到调用层
 */
public interface IDataListener<M> {
    void onSuccess(M m);

    void onFailure();
}
