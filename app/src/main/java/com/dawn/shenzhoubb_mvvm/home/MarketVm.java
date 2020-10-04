package com.dawn.shenzhoubb_mvvm.home;

import android.graphics.Color;
import android.widget.ImageView;

import com.dawn.lib_common.base.BaseViewModel;
import com.dawn.lib_common.bean.BaseResponse;
import com.dawn.lib_common.bean.HomeInfoBean;
import com.dawn.lib_common.bindingadapter.GlideApp;
import com.dawn.lib_common.http.api.ApiRepository;
import com.dawn.lib_common.util.ToastUtils;
import com.dawn.lib_common.view.SimpleBannerPageChangeListener;
import com.dawn.shenzhoubb_mvvm.RxHttpObserver;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;

import java.util.Iterator;
import java.util.List;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;

public class MarketVm extends BaseViewModel {
    public ObservableArrayList<HomeInfoBean.HomeItemBean> bannerList=new ObservableArrayList<>();
    public MutableLiveData<Integer> statusBarColor=new MutableLiveData<>(-1);
    public MutableLiveData<Object> showDialog=new MutableLiveData<>();
    public BannerImageAdapter<HomeInfoBean.HomeItemBean> bannerAdapter=new BannerImageAdapter<HomeInfoBean.HomeItemBean>(null) {
        @Override
        public void onBindView(BannerImageHolder holder, HomeInfoBean.HomeItemBean data, int position, int size) {
            ImageView imageView=holder.imageView;
            GlideApp.with(imageView.getContext()).load(data.img).placeholder(imageView.getDrawable()).error(imageView.getDrawable()).into(imageView);
        }
    };
    public OnBannerListener<HomeInfoBean.HomeItemBean> bannerListener= (data, position) -> ToastUtils.showShortToast2("===>"+position);
   public OnPageChangeListener pageChangeListener=new SimpleBannerPageChangeListener(){
       @Override
       public void onPageSelected(int position) {
           super.onPageSelected(position);
           try {
               String color="#"+bannerList.get(position).style;
               statusBarColor.postValue(Color.parseColor(color));
           }catch (Exception e){
               statusBarColor.postValue(-1);
           }

       }
   };


    public MarketVm(){
        getHomeAds();

    }
    private void getHomeAds(){
        ApiRepository.request(ApiRepository.get().getHomeAds("1,10",""))
                .subscribe(new RxHttpObserver<BaseResponse<List<HomeInfoBean>>>(this) {
                    @Override
                    public void onSuccess(BaseResponse<List<HomeInfoBean>> listBaseResponse) {
                        List<HomeInfoBean> body=listBaseResponse.body;
                        for (Iterator<HomeInfoBean> iterator = body.iterator(); iterator.hasNext(); ) {
                            HomeInfoBean next =  iterator.next();
                            switch (next.adCategoryCode){
                                case "1":
                                    bannerList.clear();
                                    bannerList.addAll(next.adInfoList);
                                    String color="#"+bannerList.get(0).style;
                                    statusBarColor.postValue(Color.parseColor(color));
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
        showDialog.postValue(true);
//        HashMap<String,Object>map=new HashMap<>();
//        map.put("class", DemandDetailActivity.class);
//        startActivity.postValue(map);
    }
}
