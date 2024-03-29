package com.hxh.dn_netframework;

/**
 * Created by HXH at 2019/9/18
 * bean
 */
public class AppVersion {
    /**
     * title : 4.5.0更新啦！
     * content : 1. 优化了阅读体验；
     2. 上线了 hyman 的课程；
     3. 修复了一些已知问题。
     * url : http://59.110.162.30/v450_imooc_updater.apk
     * md5 : 14480fc08932105d55b9217c6d2fb90b
     * versionCode : 450
     */

    private String title;
    private String content;
    private String url;
    private String md5;
    private String versionCode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    @Override
    public String toString() {
        return "AppVersion{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", md5='" + md5 + '\'' +
                ", versionCode='" + versionCode + '\'' +
                '}';
    }
}
