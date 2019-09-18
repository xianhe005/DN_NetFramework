package com.hxh.dn_netframework.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by HXH at 2019/9/18
 * json类型回调
 */
public class JsonHttpListener<M> implements IHttpListener {
    private Class<M> responseClass;
    private IDataListener<M> dataListener;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public JsonHttpListener(Class<M> responseClass, IDataListener<M> dataListener) {
        this.responseClass = responseClass;
        this.dataListener = dataListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        // inputStream流中内容为响应结果
        String content = getContent(inputStream);
        //把结果转换成对象
        final M response = JSON.parseObject(content, responseClass);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                dataListener.onSuccess(response);
            }
        });
    }

    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            content = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    @Override
    public void onFailure() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                dataListener.onFailure();
            }
        });
    }
}
