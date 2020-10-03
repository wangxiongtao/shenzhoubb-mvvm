package com.dawn.lib_common.http.api;


import com.dawn.lib_common.bean.BaseResponse;
import com.dawn.lib_common.bean.HomeInfoBean;
import com.dawn.lib_common.bean.LoadAppResBean;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/app/checkResource")
    Observable<ResponseBody> checkAppResource(@Query("timestamp") String timestamp);


    @GET("/app/checkResource")
    MutableLiveData<ResponseBody> checkAppResource1(@Query("timestamp") String timestamp);


    @GET("/app/checkResource")
    Integer checkAppResource2(@Query("timestamp") String timestamp);


    @GET("/app/checkResource")
    Observable<BaseResponse<LoadAppResBean>> checkAppResource4(@Query("timestamp") String timestamp);

    /**
     //     * 首页获取数据
     //     *
     //     * @param adType 广告类型 0-轮播图 1-社区动态 2-热门、高价、最新 3-最新播报/最新动态 4-技术达人
     //     * @param target String目标用户 0-工程师 1-客户
     //     * @return
     //     */
    @GET("/api/ads/getAds")
    Observable<BaseResponse<List<HomeInfoBean>>> getHomeAds(@Query("adCategoryCodes") String adType, @Query("deviceToken") String deviceToken);

}
