package com.dataoke.bxkadsdklib.http.retrofit;

import com.dataoke.bxkadsdklib.http.bean.FloatViewBean;
import com.dataoke.bxkadsdklib.http.bean.InfoFlowBean;
import com.dataoke.bxkadsdklib.http.bean.MyDialogBean;
import com.dataoke.bxkadsdklib.http.bean.OpenScreenBean;
import com.dataoke.bxkadsdklib.http.bean.ResponseModel;
import com.dataoke.bxkadsdklib.http.bean.SearchViewBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {
    //获取开屏广告数据
    @GET(ApiConstants.GET_OPEN_SCREEN_DATA)
    Observable<ResponseModel<OpenScreenBean>> getOpenScreenData(@QueryMap Map<String,String> map);
    //获取信息流组件数据
    @GET(ApiConstants.GET_INFO_FLOW_DATA)
    Observable<ResponseModel<InfoFlowBean>> getInfoFlowData(@QueryMap Map<String,String> map);
    //获取搜索组件信息
    @GET(ApiConstants.GET_SEARCH_DATA)
    Observable<ResponseModel<SearchViewBean>> getSearchData(@QueryMap Map<String,String> map);
    //获取弹窗组件信息
    @GET(ApiConstants.GET_DIALOG_DATA)
    Observable<ResponseModel<MyDialogBean>> getMyDialogData(@QueryMap Map<String,String> map);
    //获取浮窗组件信息
    @GET(ApiConstants.GET_FLOAT_DATA)
    Observable<ResponseModel<FloatViewBean>> getFloatData(@QueryMap Map<String,String> map);
}
