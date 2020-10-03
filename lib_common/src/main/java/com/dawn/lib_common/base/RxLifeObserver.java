package com.dawn.lib_common.base;


import com.dawn.lib_common.util.LogUtil;
import com.dawn.lib_common.util.ToastUtils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxLifeObserver<T> implements Observer<T>, LifecycleObserver, IViewModel {
    private IDisposable iDisposable;
    private Disposable disposable;
    private LifecycleOwner owner;
    private BaseViewModel baseViewModel;
    private boolean showLoading=true;


    protected RxLifeObserver(IDisposable iDisposable) {
        this.iDisposable = iDisposable;
        if (iDisposable instanceof BaseViewModel) {
            baseViewModel = (BaseViewModel) iDisposable;
        }
    }

    protected RxLifeObserver(LifecycleOwner owner) {
        this.owner = owner;
    }

    public RxLifeObserver<T> setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
        return this;
    }

    @Override
    public final void onSubscribe(Disposable d) {//防止子类重写
        this.disposable = d;
        if (this.iDisposable != null) {
            this.iDisposable.addDisposable(d);
        }
        if (this.owner != null) {
            this.owner.getLifecycle().addObserver(this);
        }
        showLoading();

        onSubscribe();
    }

    public void onSubscribe() {

    }


    @Override
    public void onNext(T t) {
        closeLoading();
    }

    @Override
    public void onError(Throwable e) {
        closeLoading();
        ToastUtils.showShortToast2(e.getLocalizedMessage());
    }


    @Override
    public void onComplete() {
        LogUtil.e("==onComplete====>");

    }

    @Override
    public void showLoading() {
        if(!showLoading){
            return;
        }
        if(baseViewModel==null){
            return;
        }
        baseViewModel.showLoading();
    }

    @Override
    public void closeLoading() {
        if(baseViewModel==null){
            return;
        }
        baseViewModel.closeLoading();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        disposable.dispose();
    }
}
