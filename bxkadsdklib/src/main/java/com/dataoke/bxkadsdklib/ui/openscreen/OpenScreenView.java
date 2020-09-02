package com.dataoke.bxkadsdklib.ui.openscreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dataoke.bxkadsdklib.R;
import com.dataoke.bxkadsdklib.http.bean.OpenScreenBean;
import com.dataoke.bxkadsdklib.http.retrofit.ApiController;
import com.dataoke.bxkadsdklib.http.retrofit.CommonObserver;
import com.dataoke.bxkadsdklib.http.retrofit.ObserverOnNextListener;
import com.dataoke.bxkadsdklib.http.retrofit.RxSchedulers;
import com.dataoke.bxkadsdklib.interfaces.IOpenScreenViewListener;
import com.dataoke.bxkadsdklib.ui.baseview.BaseView;
import com.dataoke.bxkadsdklib.util.ErrorCode;
import com.dataoke.bxkadsdklib.util.StrUtil;
import com.dataoke.bxkadsdklib.util.Util;

import java.util.List;

/**
 * 开屏广告组件
 */
public class OpenScreenView extends BaseView<IOpenScreenViewListener> {
    private RelativeLayout ac_layout;
    private LinearLayout goods_layout,tag_layout;
    private ImageView ac_img,goods_img,is_mall_icon,logo_img;;
    private TextView title_text,quan_money_text,quan_price_text,old_price_text,sall_num_text,tag_text,tag1_text;

    public OpenScreenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        View view = inflate(context, R.layout.open_screen_view_layout,this);
        goods_layout = view.findViewById(R.id.goods_layout);
        ac_layout = view.findViewById(R.id.ac_layout);
        ac_img = view.findViewById(R.id.ac_img);
        goods_img = view.findViewById(R.id.goods_img);
        is_mall_icon = view.findViewById(R.id.is_mall_icon);
        title_text = view.findViewById(R.id.title_text);
        quan_money_text = view.findViewById(R.id.quan_money_text);
        quan_price_text = view.findViewById(R.id.quan_price_text);
        old_price_text = view.findViewById(R.id.old_price_text);
        sall_num_text = view.findViewById(R.id.sall_num_text);
        tag_text = view.findViewById(R.id.tag_text);
        tag1_text = view.findViewById(R.id.tag1_text);
        tag_layout = view.findViewById(R.id.tag_layout);
        logo_img = view.findViewById(R.id.logo_img);
    }

    @Override
    public void initViewData(Context context, String comId) {
        getAcOrGoddsData(comId);
    }

    /**
     * 获取活动或商品数据
     * @param comId
     */
    private void getAcOrGoddsData(String comId) {
        ApiController.service()
                .getOpenScreenData(Util.getMyMap(comId))
                .compose(RxSchedulers.io_main())
                .flatMap(ApiController.judgeData(OpenScreenBean.class))
                .subscribe(new CommonObserver(new ObserverOnNextListener<OpenScreenBean>() {
                    @Override
                    public void onNext(OpenScreenBean openScreenBean) {
                        setBaseInfo(openScreenBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null!=listener) listener.loadFail(ErrorCode.NET_ERROR,e.getMessage());
                    }
                }));
    }

    /**
     * 适配数据
     * @param dataBean
     */
    private void setBaseInfo(OpenScreenBean dataBean) {
        try {
            if (!TextUtils.isEmpty(dataBean.getAppLogo())){
                Glide.with(getContext()).load(dataBean.getAppLogo()).apply(RequestOptions.centerCropTransform()).into(logo_img);
                Util.toOutBrowser(getContext(),dataBean.getAppLogoLinkUrl(),logo_img);
            }
            //活动
            if (dataBean.getData_type() == 2) {
                goods_layout.setVisibility(View.GONE);
                ac_layout.setVisibility(View.VISIBLE);
                Glide.with(getContext()).load(dataBean.getFront_img()).apply(RequestOptions.centerCropTransform()).into(ac_img);
                ac_layout.setOnClickListener(view -> {
                    if (null!=listener) listener.click(dataBean.getMall_url());
                });
            }else if (dataBean.getData_type()==1){  //商品
                List<OpenScreenBean.ListBean> list = dataBean.getList();
                if (list.size()>0){
                    goods_layout.setVisibility(View.VISIBLE);
                    ac_layout.setVisibility(View.GONE);
                    OpenScreenBean.ListBean listBean = list.get(0);
                    Glide.with(getContext()).load(listBean.getPic()).apply(RequestOptions.centerCropTransform()).into(goods_img);
                    is_mall_icon.setVisibility(listBean.getIstmall() == 1 ? View.VISIBLE : View.GONE);
                    title_text.setText(listBean.getDtitle());
                    double quanJine = listBean.getQuanJine();
                    double jiage = listBean.getJiage();
                    quan_money_text.setText("¥" + StrUtil.removeZero( quanJine+ ""));
                    quan_price_text.setText(StrUtil.removeZero( jiage+ ""));
                    old_price_text.setText("¥" + StrUtil.removeZero((quanJine + jiage) + ""));
                    old_price_text.getPaint().setAntiAlias(true);//抗锯齿
                    old_price_text.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                    sall_num_text.setText(StrUtil.numToQianWan(listBean.getXiaoliang()));
                    goods_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (null!=listener) listener.click(listBean.getUrl());
                        }
                    });
                    String tag = listBean.getTag();
                    String tag1 = listBean.getTag1();
                    if (TextUtils.isEmpty(tag) && TextUtils.isEmpty(tag1)) {
                        tag_layout.setVisibility(View.GONE);
                    } else {
                        tag_layout.setVisibility(View.VISIBLE);
                        if (TextUtils.isEmpty(tag)) {
                            tag_text.setVisibility(View.GONE);
                        } else {
                            tag_text.setVisibility(View.VISIBLE);
                            tag_text.setText(tag);
                        }
                        if (TextUtils.isEmpty(tag1)) {
                            tag1_text.setVisibility(View.GONE);
                        } else {
                            tag1_text.setVisibility(View.VISIBLE);
                            tag1_text.setText(tag1);
                        }
                    }
                }else {
                    if (null!=listener) listener.loadFail(ErrorCode.NET_DATA_EMPTY,"商品列表数据为空");
                }
            }
            if (null!=listener) listener.loadSuccess();
        } catch (Exception e) {
            if (null!=listener) listener.loadFail(ErrorCode.SET_BASE_INFO_ERROR,e.getMessage());
        }
    }
}
