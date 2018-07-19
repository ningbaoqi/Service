package com.shop.ningbaoqi.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SecondActivity extends AppCompatActivity {
    private Intent intent;
    private MyServiceConnection connection;
    IProxy iProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, TestService.class);
        connection = new MyServiceConnection();
    }

    public void bind(View view) {
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void unbind(View view) {
        unbindService(connection);
    }

    class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iProxy = (IProxy) service;
            iProxy.doSome();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }
}
