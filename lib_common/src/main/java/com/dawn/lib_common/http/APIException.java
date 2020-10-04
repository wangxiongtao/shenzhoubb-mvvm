package com.dawn.lib_common.http;

/**
 * Created by Dawn on 2018/10/27.
 */

public class APIException extends Exception {
    private int code;
    public APIException(int code, String message) {
        super(message);
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}
