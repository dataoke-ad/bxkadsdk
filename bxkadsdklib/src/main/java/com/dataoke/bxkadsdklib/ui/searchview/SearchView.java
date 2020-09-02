package com.dataoke.bxkadsdklib.ui.searchview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dataoke.bxkadsdklib.R;
import com.dataoke.bxkadsdklib.http.bean.SearchViewBean;
import com.dataoke.bxkadsdklib.http.retrofit.ApiController;
import com.dataoke.bxkadsdklib.http.retrofit.CommonObserver;
import com.dataoke.bxkadsdklib.http.retrofit.ObserverOnNextListener;
import com.dataoke.bxkadsdklib.http.retrofit.RxSchedulers;
import com.dataoke.bxkadsdklib.interfaces.ISearchViewListener;
import com.dataoke.bxkadsdklib.ui.baseview.BaseView;
import com.dataoke.bxkadsdklib.util.ErrorCode;
import com.dataoke.bxkadsdklib.util.Util;

import java.util.HashMap;

public class SearchView extends BaseView<ISearchViewListener> {
    private EditText edit;
    private TextView search_text;
    private String comId="";

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        View view = inflate(context, R.layout.search_view_layout, this);
        edit=view.findViewById(R.id.edit);
        search_text=view.findViewById(R.id.search_text);

        search_text.setOnClickListener(view1 ->{
            if (null!=listener){
                String s = edit.getText().toString().trim();
                if (TextUtils.isEmpty(s)) listener.searchEmpty();
                else getData(s);
            }

        } );
    }


    @Override
    public void initViewData(Context context, String comId) {
        this.comId=comId;
    }

    private void getData(String content) {
        ApiController.service()
                .getSearchData(Util.getSearchMap(comId,content))
                .compose(RxSchedulers.io_main())
                .flatMap(ApiController.judgeData(SearchViewBean.class))
                .subscribe(new CommonObserver<>(new ObserverOnNextListener<SearchViewBean>() {
                    @Override
                    public void onNext(SearchViewBean searchViewBean) {
                        try {
                            if (null!=listener){
                                listener.searchSuccess(searchViewBean.getMall_url());
                                listener.loadSuccess();
                            }
                        }catch (Exception e){
                            if (null!=listener) listener.loadFail(ErrorCode.SET_BASE_INFO_ERROR,e.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null!=listener) listener.loadFail(ErrorCode.NET_ERROR,e.getMessage());
                    }
                }));
    }
}
