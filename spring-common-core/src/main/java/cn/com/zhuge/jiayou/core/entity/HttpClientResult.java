package cn.com.zhuge.jiayou.core.entity;

import java.io.Serializable;

/**
 * httpClient响应类
 * @author 诸葛
 * @date 2022/11/30 11:03
 **/
public class HttpClientResult implements Serializable {
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult() {
    }

    public HttpClientResult(int code) {
        this.code = code;
    }

    public HttpClientResult(String content) {
        this.content = content;
    }

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }
}
