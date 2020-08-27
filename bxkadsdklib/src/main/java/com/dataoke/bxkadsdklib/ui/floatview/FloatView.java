package com.dataoke.bxkadsdklib.ui.floatview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dataoke.bxkadsdklib.R;
import com.dataoke.bxkadsdklib.http.bean.FloatViewBean;
import com.dataoke.bxkadsdklib.http.retrofit.ApiController;
import com.dataoke.bxkadsdklib.http.retrofit.CommonObserver;
import com.dataoke.bxkadsdklib.http.retrofit.ObserverOnNextListener;
import com.dataoke.bxkadsdklib.http.retrofit.RxSchedulers;
import com.dataoke.bxkadsdklib.interfaces.IFloatViewListener;
import com.dataoke.bxkadsdklib.ui.baseview.BaseView;
import com.dataoke.bxkadsdklib.util.ErrorCode;
import com.dataoke.bxkadsdklib.util.Util;

public class FloatView extends BaseView<IFloatViewListener> {
    private ImageView image,close_img;

    public FloatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        View view = inflate(context, R.layout.float_view_layout, this);
        image=view.findViewById(R.id.image);
        close_img=view.findViewById(R.id.close_img);
        close_img.setOnClickListener(view1 -> {
            if (null!=listener) listener.closeFloatView();
            setVisibility(GONE);
        });

    }

    @Override
    public void initViewData(Context context, String comId) {
        getData(context,comId);
    }

    private void getData(Context context, String comId) {
        ApiController.service()
                .getFloatData(Util.getMyMap(comId))
                .compose(RxSchedulers.io_main())
                .flatMap(ApiController.judgeData(FloatViewBean.class))
                .subscribe(new CommonObserver<>(new ObserverOnNextListener<FloatViewBean>() {
                    @Override
                    public void onNext(FloatViewBean floatViewBean) {
                        setInfo(context,floatViewBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null!=listener) listener.loadFail(ErrorCode.NET_ERROR,e.getMessage());
                    }
                }));
    }

    private void setInfo(Context context, FloatViewBean floatViewBean) {
        try {
            Glide.with(context).load(floatViewBean.getFront_img()).apply(RequestOptions.centerCropTransform()).into(image);
            image.setOnClickListener(view -> {
                if (null!=listener) listener.clickImg(floatViewBean.getMall_url());
            });
            if (null!=listener) listener.loadSuccess();
        }catch (Exception e){
            if (null!=listener) listener.loadFail(ErrorCode.SET_BASE_INFO_ERROR,e.getMessage());
        }
    }
}
