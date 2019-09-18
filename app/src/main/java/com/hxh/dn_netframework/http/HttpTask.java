package com.hxh.dn_netframework.http;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by HXH at 2019/9/18
 * http任务
 */
public class HttpTask<T> implements Runnable{
    private IHttpService mHttpService;
    //private IHttpListener mHttpListener;
    //private T mRequestInfo;

    public HttpTask(T requestInfo, String url, IHttpService httpService, IHttpListener httpListener) {
        mHttpService = httpService;
        //mHttpListener = httpListener;
        mHttpService.setUrl(url);
        mHttpService.setHttpCallBack(httpListener);

        if (requestInfo != null) {
            // 把请求信息的对象转换成json格式到网络上进行发送出去
            String requestContent = JSON.toJSONString(requestInfo);
            try {
                mHttpService.setRequestData(requestContent.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        mHttpService.execute();
    }
}
