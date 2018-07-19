package com.shop.ningbaoqi.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class SecondService extends Service {
    /**
     * 绑定时调用
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ZhongJianRen();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 解绑时调用
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void doSomething() {
        Log.d("nbq", "Service doSomething");
    }

    class ZhongJianRen extends Binder implements PublicBusness {
        @Override
        public void doSome() {
            doSomething();
        }

        public void daMaJiang() {
            Log.d("nbq", "damajiang ");
        }
    }
}
