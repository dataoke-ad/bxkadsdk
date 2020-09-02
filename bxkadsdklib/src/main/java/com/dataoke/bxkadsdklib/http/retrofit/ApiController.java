package com.dataoke.bxkadsdklib.http.retrofit;

import com.dataoke.bxkadsdklib.http.bean.ResponseModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    public static ApiService service;

    public static ApiService service(){
        if (null==service){
            synchronized (ApiController.class){
                if (null==service) service=getRetrofit().create(ApiService.class);
            }
        }
        return service;
    }

    private static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(20L, TimeUnit.SECONDS)
                .readTimeout(20L, TimeUnit.SECONDS)
                .writeTimeout(20L, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public static <T> Function<ResponseModel<T>, ObservableSource<T>> judgeData(Class<T> clazz){
        return model ->
             it -> {
                if (model.getCode().equals("200")){
                    if (null==model.getData()) {
                        try {
                            it.onNext(clazz.newInstance());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else it.onNext(model.getData());
                }
                else it.onError(new Error(model.getMsg()+"("+model.getCode()+")"));
            };
    }
}
