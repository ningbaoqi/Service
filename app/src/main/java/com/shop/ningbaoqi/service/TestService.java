package com.shop.ningbaoqi.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Zhongjianren();
    }

    private void doSomething() {
        Log.d("nbq", "doSomething");
    }

    class Zhongjianren extends Binder implements IProxy {
        @Override
        public void doSome() {
            doSomething();
        }
    }
}
