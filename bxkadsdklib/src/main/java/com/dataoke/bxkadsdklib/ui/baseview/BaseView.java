package com.dataoke.bxkadsdklib.ui.baseview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.dataoke.bxkadsdklib.interfaces.IBaseListener;

public abstract class BaseView<T extends IBaseListener> extends LinearLayout {
    public T listener;

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void setListener(T listener){
        this.listener=listener;
    }

    protected void init(Context context){}

    public void initViewData(Context context, String comId){}
}
