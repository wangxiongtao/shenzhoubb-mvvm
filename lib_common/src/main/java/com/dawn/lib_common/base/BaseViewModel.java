package com.dawn.lib_common.base;


import com.dawn.lib_common.http.APIException;
import com.dawn.lib_common.util.LogUtil;

import java.util.HashMap;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends LifeViewModel implements IDisposable,IViewModel {
    private CompositeDisposable disposable;
    public MutableLiveData<Boolean> loadingEvent=new MutableLiveData<>();
    public MutableLiveData<APIException> apiExceptionEvent=new MutableLiveData<>();
    public MutableLiveData<HashMap<String,Object>> startActivity=new MutableLiveData<>();



    @Override
    public void addDisposable(Disposable d) {
        if(disposable==null){
            disposable=new CompositeDisposable();
        }
        disposable.add(d);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtil.e("===OnLifecycleEvent==onCleared==>");
        if(disposable!=null){
            disposable.clear();
        }
    }

    @Override
    public void showLoading() {
        loadingEvent.postValue(true);

    }

    @Override
    public void closeLoading() {
        loadingEvent.postValue(false);
    }

}
