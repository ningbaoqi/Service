package com.shop.ningbaoqi.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FirstActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        intent.setClass(this, RecordService.class);
    }
    public void click(View view) {
        startService(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }
    public void click1(View view) {
        stopService(intent);
    }
}
