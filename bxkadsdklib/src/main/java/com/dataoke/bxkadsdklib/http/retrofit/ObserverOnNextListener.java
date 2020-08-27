package com.dataoke.bxkadsdklib.http.retrofit;

public interface ObserverOnNextListener<T> {
    void onNext(T t);
    void onError(Throwable e);
}
