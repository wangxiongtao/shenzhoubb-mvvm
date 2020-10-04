package com.dawn.shenzhoubb_mvvm.home;

import android.graphics.Color;

import com.dawn.lib_common.base.BaseViewModel;

import androidx.lifecycle.MutableLiveData;

public class HomeVm extends BaseViewModel {
    public MutableLiveData<Integer> statusBarColor=new MutableLiveData<>(Color.parseColor("#FF6600"));




}
