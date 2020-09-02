package com.dataoke.bxkadsdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dataoke.bxkadsdklib.interfaces.IOpenScreenViewListener;
import com.dataoke.bxkadsdklib.ui.openscreen.OpenScreenView;

public class OpenScreenActivity extends AppCompatActivity {
    private OpenScreenView open_screen_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);
        open_screen_view=findViewById(R.id.open_screen_view);
        open_screen_view.initViewData(this,ApiParams.OPEN_SCREEN_COMID);
        open_screen_view.setListener(new IOpenScreenViewListener() {
            @Override
            public void click(String url) {
                startActivity(new Intent(OpenScreenActivity.this,WebViewActivity.class).putExtra("url",url));
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
