package com.yscoco.uppernest.api;

/**
 * Created by ZhangZeZhi on 2018\11\23 0023.
 */

public class BaseResp<T> {
    public T data;
    public String code;
    public String msg;

    public T getData() {
        return data;
    }

    public BaseResp setData(T data) {
        this.data = data;
        return this;
    }

    public String getCode() {
        return code;
    }

    public BaseResp setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResp setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "BaseResp{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

}
