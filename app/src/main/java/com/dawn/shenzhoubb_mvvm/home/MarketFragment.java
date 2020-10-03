package com.dawn.shenzhoubb_mvvm.home;

import android.os.Bundle;

import com.dawn.lib_common.base.BaseFragment;
import com.dawn.shenzhoubb_mvvm.BR;
import com.dawn.shenzhoubb_mvvm.R;
import com.dawn.shenzhoubb_mvvm.databinding.FragmentMarketBinding;


public class MarketFragment extends BaseFragment<FragmentMarketBinding,MarketVm> {

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

    }

    @Override
    public int initVariableId() {
        return BR.marketVm;
    }
}