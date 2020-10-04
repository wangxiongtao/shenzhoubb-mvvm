package com.dawn.lib_common.bindingadapter;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class ViewBindingAdapter {

//    @BindingAdapter("lineManager")
//    public static void setLineManager(RecyclerView recyclerView,DividerLine.LineDrawMode type) {
//        recyclerView.addItemDecoration(new DividerLine(recyclerView.getContext(), type));
//    }
//    @BindingAdapter({"dataList","adapter"})
//    public static <T> void setDataList(RecyclerView recyclerView, List<T> list, ItemAdapter<T> adapter){
//       ItemAdapter<T>  itemAdapter= (ItemAdapter<T>) recyclerView.getAdapter();
//        if(itemAdapter==null){
//            itemAdapter=adapter;
//            adapter.setList(list);
//            recyclerView.setAdapter(adapter);
//        }
//        itemAdapter.notifyDataSetChanged();
//
//    }
    @BindingAdapter("imageUrl")
    public static <T> void setImageUrl(ImageView imageView, String imageUrl){
        imageUrl="https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2839262297,1897381364&fm=26&gp=0.jpg";
        GlideApp.with(imageView.getContext()).load(imageUrl).placeholder(imageView.getDrawable()).error(imageView.getDrawable()).into(imageView);

    }
    @BindingAdapter(value = {"bannerAdapter","bannerListener","bannerChangeListener"},requireAll = false)
    public static <T,BA extends BannerAdapter<T,VH>,VH extends RecyclerView.ViewHolder>void setBannerAdapter(Banner<T,BA> banner, BA adapter, OnBannerListener<T>listener, OnPageChangeListener pageChangeListener){
        Context context=banner.getContext();
        if(context instanceof LifecycleOwner){
            banner.addBannerLifecycleObserver((LifecycleOwner) banner.getContext());
        }
        banner.setIndicator(new CircleIndicator(banner.getContext()));
        banner.setAdapter(adapter);
        banner.setOnBannerListener(listener);
        banner.addOnPageChangeListener(pageChangeListener);
    }
    @BindingAdapter("bannerList")
    public static <T,BA extends BannerAdapter<T,VH>,VH extends RecyclerView.ViewHolder>void setBannerList(Banner<T,BA> banner, List<T> list){
        banner.setDatas(list);
    }
}
