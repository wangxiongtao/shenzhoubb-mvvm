package com.dawn.lib_common.http.api;


import com.dawn.lib_common.bean.BaseResponse;
import com.dawn.lib_common.bean.HomeInfoBean;
import com.dawn.lib_common.bean.LoadAppResBean;
import com.dawn.lib_common.http.APIException;
import com.dawn.lib_common.http.HandlerException;
import com.dawn.lib_common.http.HttpManager;
import com.dawn.lib_common.util.RXUtil;
import com.youth.banner.util.LogUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ApiRepository {

 public static ApiInterface get(){
     return HttpManager.getInstance().getApiInterface();
 }
    public static <T> ObservableTransformer<BaseResponse<T>, BaseResponse<T>> mainThreadAndError() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(t -> {
                    if (!t.isValid()) {
                        throw new APIException(t.getCode(), t.getMessage());
                    }
                    return t;
                }).onErrorResumeNext((Function<Throwable, Observable<BaseResponse<T>>>) e -> {
                    LogUtils.e("===请求异常原始错误信息==>" + e.getMessage());
                    return Observable.error(HandlerException.handlerException(e));
                });
    }

    public static Observable<ResponseBody> checkAppResource(String time) {
        return get().checkAppResource(time)
                .compose(RXUtil.mainThread());
    }
    public static  Observable<BaseResponse<LoadAppResBean>> checkAppResource4(String time) {
        return get().checkAppResource4(time)
                .compose(RXUtil.mainThread());
    }
    public static  Observable<BaseResponse<List<HomeInfoBean>>> getHomeAds(String adType, String deviceToken) {
        return get().getHomeAds(adType,deviceToken)
                .compose(mainThreadAndError());
    }
    public static  <T>Observable<BaseResponse<T>> request( Observable<BaseResponse<T>>observable) {
        return observable.compose(mainThreadAndError());
    }


}
