package com.shop.ningbaoqi.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Intent intent = new Intent(this, MyService.class);

    public void startservice(View view) {
        /**
         * 显式启动服务
         * */
        startService(intent);
    }

    public void stopservice(View view) {
        /**
         * 停止服务
         * */
        stopService(intent);
    }
}
