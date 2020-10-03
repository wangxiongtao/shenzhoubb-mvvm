package com.dawn.lib_common.base;

import io.reactivex.disposables.Disposable;

public interface IDisposable {
    void addDisposable(Disposable disposable);
}
