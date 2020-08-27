package com.dataoke.bxkadsdklib.ui.infoflow;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dataoke.bxkadsdklib.R;
import com.dataoke.bxkadsdklib.http.bean.InfoFlowBean;
import com.dataoke.bxkadsdklib.http.retrofit.ApiController;
import com.dataoke.bxkadsdklib.http.retrofit.CommonObserver;
import com.dataoke.bxkadsdklib.http.retrofit.ObserverOnNextListener;
import com.dataoke.bxkadsdklib.http.retrofit.RxSchedulers;
import com.dataoke.bxkadsdklib.interfaces.InfoFlowViewListener;
import com.dataoke.bxkadsdklib.ui.baseview.BaseView;
import com.dataoke.bxkadsdklib.util.ErrorCode;
import com.dataoke.bxkadsdklib.util.Util;

import java.util.ArrayList;
import java.util.List;

public class InfoFlowView extends BaseView<InfoFlowViewListener> {
    private ImageView ac_img;
    private RecyclerView recyclerView;

    private InfoFlowAdapter infoFlowAdapter;
    private List<InfoFlowBean.ListBean> list=new ArrayList<>();

    public InfoFlowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        View view = inflate(context, R.layout.info_flow_view_layout,this);
        ac_img=view.findViewById(R.id.ac_img);
        recyclerView=view.findViewById(R.id.recyclerView);

    }

    @Override
    public void initViewData(Context context, String comId) {
        getData(context,comId);
    }

    private void getData(Context context, String comId) {
        ApiController.service()
                .getInfoFlowData(Util.getMyMap(comId))
                .compose(RxSchedulers.io_main())
                .flatMap(ApiController.judgeData(InfoFlowBean.class))
                .subscribe(new CommonObserver<>(new ObserverOnNextListener<InfoFlowBean>() {
                    @Override
                    public void onNext(InfoFlowBean infoFlowBean) {
                        setInfo(context,infoFlowBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null!=listener) listener.loadFail(ErrorCode.NET_ERROR,e.getMessage());
                    }
                }));

    }

    private void setInfo(Context context, InfoFlowBean infoFlowBean) {
        try {
            //活动
            if (infoFlowBean.getData_type()==2){
                ac_img.setVisibility(VISIBLE);
                recyclerView.setVisibility(GONE);
                Glide.with(context).load(infoFlowBean.getFront_img()).apply(RequestOptions.centerCropTransform()).into(ac_img);
                ac_img.setOnClickListener(view -> {
                    if (null!=listener) listener.clickAd(infoFlowBean.getMall_url());
                });
            }else if (infoFlowBean.getData_type()==1){  //商品
                ac_img.setVisibility(GONE);
                recyclerView.setVisibility(VISIBLE);
                if (null==list) list=new ArrayList<>();
                list.clear();
                list.addAll(infoFlowBean.getList());
                recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                infoFlowAdapter=new InfoFlowAdapter(context, list, (postion, s) -> {
                    if (null!=listener) listener.clickGoodsItem(postion, s);
                });
                recyclerView.setAdapter(infoFlowAdapter);

            }
            if (null!=listener) listener.loadSuccess();
        }catch (Exception e){
            if (null!=listener) listener.loadFail(ErrorCode.SET_BASE_INFO_ERROR,e.getMessage());
        }
    }
}
