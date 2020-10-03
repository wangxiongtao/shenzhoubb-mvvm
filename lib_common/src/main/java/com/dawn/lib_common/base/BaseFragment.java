package com.dawn.lib_common.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseFragment<VDB extends ViewDataBinding,VM extends BaseViewModel> extends Fragment {
    private VM viewModel;
    private VDB viewDataBinding;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewDataBinding=DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        viewDataBinding.setLifecycleOwner(this);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handlerVM();
        progressDialog = new ProgressDialog(requireActivity());
        receiveLiveData();
        initData(savedInstanceState);
    }

    
    private void handlerVM() {
        Class<BaseViewModel> viewModelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            viewModelClass = (Class<BaseViewModel>) ((ParameterizedType) type).getActualTypeArguments()[1];//获取第1个注解即VM的注解类型
        } else {
            //使用父类的类型
            viewModelClass = BaseViewModel.class;
        }
        viewModel = (VM) new ViewModelProvider(this).get(viewModelClass);//fragment自己的VM 不是Activity
        if(initVariableId()>0){
            getLifecycle().addObserver(viewModel);
            viewDataBinding.setVariable(initVariableId(),viewModel);
        }

    }

    private void receiveLiveData(){
        viewModel.loadingEvent.observe(this, aBoolean -> {
            if (aBoolean) {
                progressDialog.show();
            } else {
                progressDialog.dismiss();
            }
        });
        viewModel.startActivity.observe(this, stringObjectHashMap -> {
            Class<?> cls = (Class<?>) stringObjectHashMap.get("class");
            Bundle extras = (Bundle) stringObjectHashMap.get("bundle");
            if(extras==null){
                extras=new Bundle();
            }
            Intent intent=new Intent(requireActivity(),cls);
            intent.putExtras(extras);
            startActivity(intent);

        });
    }
    public VM getViewModel() {
        return viewModel;
    }

    public VDB getViewDataBinding() {
        return viewDataBinding;
    }

    public abstract int getLayoutId();


    public abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public  int initVariableId(){
        return -1;
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(viewDataBinding!=null){
            viewDataBinding.unbind();
        }

    }
}
