package com.dataoke.bxkadsdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dataoke.bxkadsdklib.interfaces.InfoFlowViewListener;
import com.dataoke.bxkadsdklib.ui.infoflow.InfoFlowView;

public class InfoFlowActivity extends AppCompatActivity {
    private InfoFlowView info_flow_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_flow);
        info_flow_view=findViewById(R.id.info_flow_view);
        info_flow_view.initViewData(this,ApiParams.INFO_FLOW_COMID);
        info_flow_view.setListener(new InfoFlowViewListener() {
            @Override
            public void clickAd(String url) {
                startActivity(new Intent(InfoFlowActivity.this,WebViewActivity.class).putExtra("url",url));
            }

            @Override
            public void clickGoodsItem(int position, String url) {
                startActivity(new Intent(InfoFlowActivity.this,WebViewActivity.class).putExtra("url",url));
            }

            @Override
            public void loadSuccess() {

            }

            @Override
            public void loadFail(String code, String msg) {

            }
        });
    }

    public void refreshdata(View view) {
        info_flow_view.initViewData(this,ApiParams.INFO_FLOW_COMID);
    }
}