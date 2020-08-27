package com.dataoke.bxkadsdk;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toad(View view) {
        startActivity(new Intent(this,OpenScreenActivity.class));
    }

    public void infoflow(View view) {
        startActivity(new Intent(this,InfoFlowActivity.class));
    }

    public void search(View view) {
        startActivity(new Intent(this,SearchActivity.class));
    }

    public void mydialog(View view) {
        startActivity(new Intent(this,MyDialogActivity.class));
    }

    public void floatview(View view) {
        startActivity(new Intent(this,FloatActivity.class));
    }
}