package com.dawn.shenzhoubb_mvvm;

import com.dawn.lib_common.base.IDisposable;
import com.dawn.lib_common.base.IViewModel;
import com.dawn.lib_common.base.RxLifeObserver;
import com.dawn.lib_common.http.APIException;
import com.dawn.lib_common.util.ToastUtils;

import androidx.lifecycle.LifecycleOwner;

public abstract class RxHttpObserver<T> extends RxLifeObserver<T> implements IViewModel {
    private boolean showLoading = true;

    protected RxHttpObserver(IDisposable iDisposable) {
        super(iDisposable);
    }

    protected RxHttpObserver(LifecycleOwner owner) {
        super(owner);
    }

    public RxLifeObserver<T> setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
        return this;
    }

    @Override
    public void onSubscribe() {
        super.onSubscribe();
        showLoading();
    }

    @Override
    public void onNext(T t) {
        closeLoading();
        onSuccess(t);
    }

    @Override
    public final void onError(Throwable e) {
        super.onError(e);
        closeLoading();
        if (e instanceof APIException) {
            APIException exception = (APIException) e;
            onError(exception);
        } else {
            ToastUtils.showShortToast2(e.getLocalizedMessage());
        }

    }

    @Override
    public void showLoading() {
        if (!showLoading) {
            return;
        }
        if (baseViewModel == null) {
            return;
        }
        baseViewModel.showLoading();
    }

    @Override
    public void closeLoading() {
        if (baseViewModel == null) {
            return;
        }
        baseViewModel.closeLoading();
    }


    public abstract void onSuccess(T t);

    public void onError(APIException exception) {
        if (APIExceptionHandler.handlerApiException(null, exception)) {
            baseViewModel.apiExceptionEvent.postValue(exception);
            return;
        }
        ToastUtils.showShortToast2(exception.getLocalizedMessage());
    }

    ;
}
