package com.dataoke.bxkadsdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.dataoke.bxkadsdklib.interfaces.ISearchViewListener;
import com.dataoke.bxkadsdklib.ui.searchview.SearchView;

public class SearchActivity extends AppCompatActivity {
    private SearchView search_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search_view=findViewById(R.id.search_view);
        search_view.initViewData(this,ApiParams.SEARCH_COMID);
        search_view.setListener(new ISearchViewListener() {
            @Override
            public void searchSuccess(String url) {
                startActivity(new Intent(SearchActivity.this,WebViewActivity.class).putExtra("url",url));
            }

            @Override
            public void searchEmpty() {
                Toast.makeText(SearchActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
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