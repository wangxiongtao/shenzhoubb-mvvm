package com.dawn.lib_common.util;

import com.dawn.lib_common.bean.BaseResponse;
import com.dawn.lib_common.http.APIException;
import com.dawn.lib_common.http.HandlerException;
import com.youth.banner.util.LogUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RXUtil {
    public static <T> ObservableTransformer<T, T> mainThread() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}
