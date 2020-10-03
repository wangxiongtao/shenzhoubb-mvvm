package com.dawn.shenzhoubb_mvvm.home;

import android.os.Bundle;

import com.dawn.lib_common.base.BaseFragment;
import com.dawn.lib_common.util.FragmentUtil;
import com.dawn.shenzhoubb_mvvm.BR;
import com.dawn.shenzhoubb_mvvm.R;
import com.dawn.shenzhoubb_mvvm.databinding.FragmentHomeLayoutBinding;

public class HomeFragment extends BaseFragment<FragmentHomeLayoutBinding,HomeVm> {
    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        FragmentUtil.replaceFragment(getChildFragmentManager(),MarketFragment.newInstance(),R.id.container_fl);


    }

    @Override
    public int initVariableId() {
        return BR.homeVm;
    }
}
