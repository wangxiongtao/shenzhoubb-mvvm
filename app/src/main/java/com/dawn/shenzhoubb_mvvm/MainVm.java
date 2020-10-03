package com.dawn.shenzhoubb_mvvm;

import com.dawn.lib_common.base.BaseViewModel;
import com.dawn.lib_common.util.LogUtil;
import com.dawn.shenzhoubb_mvvm.home.HomeFragment;
import com.dawn.shenzhoubb_mvvm.order.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

public class MainVm extends BaseViewModel {
    private List<Fragment> fragmentList;
    public MutableLiveData<Integer>showPosition=new MutableLiveData<>();

   public MainVm(){
       LogUtil.e("=====>mainVm=init==>");
       fragmentList=new ArrayList<>();
       fragmentList.add(HomeFragment.newInstance());
       fragmentList.add(OrderFragment.newInstance());
       showPosition.postValue(0);
   }


    public List<Fragment> getFragmentList() {
        return fragmentList;
    }


    public void showFragment(int position){
       showPosition.postValue(position);
    }

}
