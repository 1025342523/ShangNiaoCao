package com.yscoco.uppernest.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public abstract class HttpObserver<T> implements Observer<T> {

    /*public HttpObserver(Activity context) {

    }*/

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onNext(T t);

    @Override
    public abstract void onError(Throwable e);

    @Override
    public void onComplete() {

    }
}
