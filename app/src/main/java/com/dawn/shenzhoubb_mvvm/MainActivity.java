package com.dawn.shenzhoubb_mvvm;

import android.os.Bundle;

import com.dawn.lib_common.base.BaseActivity;
import com.dawn.lib_common.util.FragmentUtil;
import com.dawn.shenzhoubb_mvvm.databinding.ActivityMainBinding;

import androidx.lifecycle.Observer;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainVm> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getViewModel().showPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                showFragment(integer);
            }
        });



    }

    @Override
    public int initVariableId() {
        return BR.mainVm;
    }

    private void showFragment(int position){
        FragmentUtil.showFragment(getSupportFragmentManager(),position,getViewModel().getFragmentList(),R.id.fragment_container_layout);
    }

}