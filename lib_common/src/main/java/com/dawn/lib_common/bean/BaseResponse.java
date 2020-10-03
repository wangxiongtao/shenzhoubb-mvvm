package com.dawn.lib_common.bean;



/**
 * Created by fw-soc on 2018/9/19.
 */

public class BaseResponse<T> {
    public String result;
    public String message;
    private int code;//
    public T body;
    public boolean isValid() {
        return 0 == code || "success".equals(result);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
