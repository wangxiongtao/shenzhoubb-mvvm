package com.dawn.shenzhoubb_mvvm.order;

import android.os.Bundle;

import com.dawn.lib_common.base.BaseFragment;
import com.dawn.shenzhoubb_mvvm.R;

public class OrderFragment extends BaseFragment {

    public static OrderFragment newInstance() {

        Bundle args = new Bundle();

        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_layout;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
