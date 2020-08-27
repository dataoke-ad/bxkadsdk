package com.dataoke.bxkadsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dataoke.bxkadsdklib.interfaces.IMyDialogListener;
import com.dataoke.bxkadsdklib.ui.dialog.MyDialogView;

public class MyDialogActivity extends AppCompatActivity {
    private MyDialogView my_dialog_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);
        my_dialog_view=findViewById(R.id.my_dialog_view);
        my_dialog_view.initViewData(this,ApiParams.DIALOG_COMID);
        my_dialog_view.setListener(new IMyDialogListener() {
            @Override
            public void clickImg(String link) {

            }

            @Override
            public void clickCloseDialog() {

            }

            @Override
            public void loadSuccess() { }

            @Override
            public void loadFail(String code, String msg) { }
        });
    }
}