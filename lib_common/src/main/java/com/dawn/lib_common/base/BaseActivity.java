package com.dawn.lib_common.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.dawn.lib_common.util.StatusBarUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseActivity<VDB extends ViewDataBinding,VM extends BaseViewModel> extends AppCompatActivity {
    private VM viewModel;
    private VDB viewDataBinding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setStatusBarColor(this, Color.parseColor("#FF6600"));
        super.onCreate(savedInstanceState);
        handlerVDB();
        handlerVM();
        progressDialog = new ProgressDialog(this);
        receiveLiveData();
        initData(savedInstanceState);
    }


    private void handlerVDB() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewDataBinding.setLifecycleOwner(this);//可以使用liveData对XMl数据更新

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
        viewModel = (VM) new ViewModelProvider(this).get(viewModelClass);
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
            Intent intent=new Intent(this,cls);
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
    protected void onDestroy() {
        super.onDestroy();
        if(viewDataBinding!=null){
            viewDataBinding.unbind();
        }
    }
}
