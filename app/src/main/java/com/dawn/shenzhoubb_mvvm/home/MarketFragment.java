package com.dawn.shenzhoubb_mvvm.home;

import android.os.Bundle;

import com.dawn.lib_common.base.BaseFragment;
import com.dawn.shenzhoubb_mvvm.BR;
import com.dawn.shenzhoubb_mvvm.R;
import com.dawn.shenzhoubb_mvvm.databinding.FragmentMarketBinding;
import com.dawn.shenzhoubb_mvvm.dialog.MyDialog;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


public class MarketFragment extends BaseFragment<FragmentMarketBinding,MarketVm> {

    private HomeVm homeVm;
    public static MarketFragment newInstance() {

        Bundle args = new Bundle();

        MarketFragment fragment = new MarketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        homeVm=new ViewModelProvider(requireParentFragment()).get(HomeVm.class);
        getViewModel().statusBarColor.observe(this, color -> {
            homeVm.statusBarColor.postValue(color);

        });
        getViewModel().showDialog.observe(this, new Observer<Object>() {

            @Override
            public void onChanged(Object o) {
                MyDialog dialog=new MyDialog(requireActivity());
                dialog.setTitle("title");
                dialog.show();


            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            getViewDataBinding().banner.stop();
        }else {
            getViewDataBinding().banner.start();
        }

    }

    @Override
    public int initVariableId() {
        return BR.marketVm;
    }
}