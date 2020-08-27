package com.dataoke.bxkadsdklib.http.retrofit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CommonObserver<T> implements Observer<T> {
    private Disposable d;
    private ObserverOnNextListener<T> listener;

    public CommonObserver(ObserverOnNextListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d=d;
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        listener.onError(e);
    }

    @Override
    public void onComplete() {

    }

    public boolean isDisposed(){
        return null==d||d.isDisposed();
    }

    public void dispose(){
        if (!isDisposed()) d.dispose();
    }
}
