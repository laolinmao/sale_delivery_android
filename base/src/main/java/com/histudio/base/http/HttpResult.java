package com.histudio.base.http;

/**
 * Created by ljh on 16/3/5.
 */
public class HttpResult<T> {

    private int code;

    private String msg;

    //用来模仿Data
    private T data;

    public int getError_code() {
        return code;
    }

    public void setError_code(int error_code) {
        this.code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "error_code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


}
