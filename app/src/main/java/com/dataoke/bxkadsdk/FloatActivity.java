package com.dataoke.bxkadsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dataoke.bxkadsdklib.interfaces.IFloatViewListener;
import com.dataoke.bxkadsdklib.ui.floatview.FloatView;

public class FloatActivity extends AppCompatActivity {
    private FloatView float_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);
        float_view=findViewById(R.id.float_view);
        float_view.initViewData(this,ApiParams.FLOAT_COMID);
        float_view.setListener(new IFloatViewListener() {
            @Override
            public void clickImg(String link) {

            }

            @Override
            public void closeFloatView() {

            }

            @Override
            public void loadSuccess() {

            }

            @Override
            public void loadFail(String code, String msg) {

            }
        });
    }
}