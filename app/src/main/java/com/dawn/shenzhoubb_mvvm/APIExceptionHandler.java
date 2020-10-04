package com.dawn.shenzhoubb_mvvm;

import com.dawn.lib_common.http.APIException;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Dawn on 2018/11/30.
 * 针对接口的BaseResponse中code值的全局的接口异常处理
 * 主要是根据错误码做出响应的处理比如 弹框 或者直接跳转
 */

public class APIExceptionHandler {
    /**
     * 错误代码
     * 1000-2000 订单类错误
     * 2000-3000 用户类错误
     * 2001 客户（工程师）未实名认证通过（包括头像昵称未填写）
     * 2002 工程师未阅读服务手册
     */
    static int CODE_2001 = 2001;
    static int CODE_2005 = 2005;
    static int CODE_2002 = 2002;
    static int CODE_2003 = 2003;
    static int CODE_2004 = 2004;
    static int CODE_4005 = 4005;

    public static boolean handlerApiException(AppCompatActivity activity, APIException e) {
        int code=e.getCode();
        String message=e.getMessage();
        switch (code){
            case 401:
                if(activity==null){
                    return true;
                }


                return true;
        }
        return true;
    }

}
