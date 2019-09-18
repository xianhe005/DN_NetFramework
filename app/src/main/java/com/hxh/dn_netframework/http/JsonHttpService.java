package com.hxh.dn_netframework.http;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HXH at 2019/9/18
 * json请求
 */
public class JsonHttpService implements IHttpService {
    private String url;
    private byte[] requestData;
    private IHttpListener httpListener;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    private HttpURLConnection urlConnection = null;

    @Override
    public void execute() {
        //进行网络的真实操作实现
        httUrlconnPost();
    }

    private void httUrlconnPost() {
        OutputStream os = null;
        BufferedOutputStream bos = null;
        try {
            URL url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(6000);//连接的超时时间
            urlConnection.setUseCaches(false);//不使用缓存
            urlConnection.setInstanceFollowRedirects(true);//设置本次连接是否自动处理重定向
            urlConnection.setReadTimeout(3000);//响应的超时时间
            urlConnection.setDoInput(true);//设置是否可以写入数据
            urlConnection.setDoOutput(true);//设置是否可以输出数据
            urlConnection.setRequestMethod("POST");//设置请求的方式
            urlConnection.setRequestProperty("Content-Type","application/json;charset=utf-8");//发送json类型数据
            urlConnection.connect();//连接

            // 使用字节流发送数据
             os = urlConnection.getOutputStream();
            bos = new BufferedOutputStream(os);
            if (requestData != null) {
                bos.write(requestData); //和框架有关的
            }
            bos.flush();//刷新缓冲区,发送数据
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = urlConnection.getInputStream();
                httpListener.onSuccess(is);//和框架有关
            } else {
                httpListener.onFailure();//和框架有关
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpListener.onFailure();//和框架有关
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    @Override
    public void setHttpCallBack(IHttpListener httpCallBack) {
        this.httpListener = httpCallBack;
    }
}
