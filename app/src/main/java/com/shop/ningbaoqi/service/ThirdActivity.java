package com.shop.ningbaoqi.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {
    PublicBusness busness;
    private Intent intent;
    private MyServiceConnection connection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, SecondService.class);
        connection = new MyServiceConnection();
    }

    public void startservice(View view) {
        /**
         * 绑定服务，多次调用不会进行重复绑定
         * */
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void stopservice(View view) {
        /**
         * 解绑服务
         * */
        unbindService(connection);
    }

    class MyServiceConnection implements ServiceConnection {
        /**
         * 服务连接成功时，即成功拿到ZhongJianRen对象，此方法调用
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            busness = (PublicBusness) service;
            busness.doSome();
        }

        /**
         * 服务失去连接时，此方法调用,当正常解绑时，不会调用此方法，
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }
}
