package com.dawn.shenzhoubb_mvvm.home;

import android.widget.ImageView;

import com.dawn.lib_common.base.BaseViewModel;
import com.dawn.lib_common.base.RxLifeObserver;
import com.dawn.lib_common.bean.BaseResponse;
import com.dawn.lib_common.bean.HomeInfoBean;
import com.dawn.lib_common.bindingadapter.GlideApp;
import com.dawn.lib_common.http.api.ApiRepository;
import com.dawn.shenzhoubb_mvvm.demand.DemandDetailActivity;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import androidx.databinding.ObservableArrayList;

public class MarketVm extends BaseViewModel {
    public ObservableArrayList<HomeInfoBean.HomeItemBean> bannerList=new ObservableArrayList<>();
    public BannerImageAdapter<HomeInfoBean.HomeItemBean> bannerAdapter=new BannerImageAdapter<HomeInfoBean.HomeItemBean>(null) {
        @Override
        public void onBindView(BannerImageHolder holder, HomeInfoBean.HomeItemBean data, int position, int size) {
            ImageView imageView=holder.imageView;
            GlideApp.with(imageView.getContext()).load(data.img).placeholder(imageView.getDrawable()).error(imageView.getDrawable()).into(imageView);
        }
    };


    public MarketVm(){
        getHomeAds();

    }
    private void getHomeAds(){
        ApiRepository.request(ApiRepository.get().getHomeAds("1,10",""))
                .subscribe(new RxLifeObserver<BaseResponse<List<HomeInfoBean>>>(this){
                    @Override
                    public void onNext(BaseResponse<List<HomeInfoBean>> listBaseResponse) {
                        super.onNext(listBaseResponse);
                        List<HomeInfoBean> body=listBaseResponse.body;
                        for (Iterator<HomeInfoBean> iterator = body.iterator(); iterator.hasNext(); ) {
                            HomeInfoBean next =  iterator.next();
                            switch (next.adCategoryCode){
                                case "1":
                                    bannerList.clear();
                                    bannerList.addAll(next.adInfoList);
                                    break;
                                case "10":
                                    break;
                            }
                        }
                    }
                });

    }
    public void add(){
//        getHomeAds();
        HashMap<String,Object>map=new HashMap<>();
        map.put("class", DemandDetailActivity.class);
        startActivity.postValue(map);
    }
}
