package com.dawn.lib_common.base;


import com.dawn.lib_common.util.LogUtil;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class RxLifeObserver<T> implements Observer<T>, LifecycleObserver {
    private IDisposable iDisposable;
    private Disposable disposable;
    private LifecycleOwner owner;
    protected BaseViewModel baseViewModel;


    protected RxLifeObserver(IDisposable iDisposable) {
        this.iDisposable = iDisposable;
        if (iDisposable instanceof BaseViewModel) {
            baseViewModel = (BaseViewModel) iDisposable;
        }
    }

    protected RxLifeObserver(LifecycleOwner owner) {
        this.owner = owner;
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
        onSubscribe();
    }

    public void onSubscribe() {

    }


    @Override
    public void onError(Throwable e) {

    }


    @Override
    public void onComplete() {
        LogUtil.e("==onComplete====>");

    }



    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        disposable.dispose();
    }
}
