package com.dataoke.bxkadsdklib.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.dataoke.bxkadsdklib.http.bean.MyDialogBean;
import com.dataoke.bxkadsdklib.http.retrofit.ApiController;
import com.dataoke.bxkadsdklib.http.retrofit.CommonObserver;
import com.dataoke.bxkadsdklib.http.retrofit.ObserverOnNextListener;
import com.dataoke.bxkadsdklib.http.retrofit.RxSchedulers;
import com.dataoke.bxkadsdklib.interfaces.IMyDialogListener;
import com.dataoke.bxkadsdklib.ui.baseview.BaseView;
import com.dataoke.bxkadsdklib.util.ErrorCode;
import com.dataoke.bxkadsdklib.util.Util;

public class MyDialogView extends BaseView<IMyDialogListener> {
    public MyDialogView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initViewData(Context context, String comId) {
        getData(context,comId);
    }

    private void getData(Context context, String comId) {
        ApiController.service()
                .getMyDialogData(Util.getMyMap(comId))
                .compose(RxSchedulers.io_main())
                .flatMap(ApiController.judgeData(MyDialogBean.class))
                .subscribe(new CommonObserver<>(new ObserverOnNextListener<MyDialogBean>() {
                    @Override
                    public void onNext(MyDialogBean myDialogBean) {
                        showDialog(context,myDialogBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null!=listener) listener.loadFail(ErrorCode.NET_ERROR,e.getMessage());
                    }
                }));
    }

    private void showDialog(Context context, MyDialogBean bean) {
        try {
            DialogAct.Builder builderAct = new DialogAct.Builder(context);
            builderAct.setCloseBtnClick(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (null!=listener) listener.clickCloseDialog();
                    dialog.dismiss();
                }
            });
            builderAct.setContentClick(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (null!=listener) listener.clickImg(bean.getMall_url());
                    dialog.dismiss();
                }
            });
            builderAct.setImgUrl(bean.getFront_img());
            DialogAct dialogAct = builderAct.create();
            // 根据自己需要 设置是否 在不是类容区域点击 消失弹窗
            dialogAct.setCanceledOnTouchOutside(false);
            dialogAct.show();
            if (null!=listener) listener.loadSuccess();
        }catch (Exception e){
            if (null!=listener) listener.loadFail(ErrorCode.SET_BASE_INFO_ERROR,e.getMessage());
        }
    }
}
