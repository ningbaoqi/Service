package com.shop.ningbaoqi.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class RecordService extends Service {
    private MediaRecorder recorder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 获取电话管理器对象
         * */
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        /**
         * 监听电话状态
         * */
        manager.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    class MyListener extends PhoneStateListener {
        /**
         * 一旦电话状态改变此方法调用
         */
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                /**
                 * 空闲状态
                 * */
                case TelephonyManager.CALL_STATE_IDLE:
                    if (recorder != null) {
                        recorder.stop();
                        recorder.release();
                        recorder = null;
                    }
                    break;
                /**
                 * 响铃状态
                 * */
                case TelephonyManager.CALL_STATE_RINGING:
                    if (recorder == null) {
                        recorder = new MediaRecorder();
                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        recorder.setOutputFile("sdcard/luyin.3gp");
                        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        try {
                            recorder.prepare();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                /**
                 * 摘机即接听状态
                 * */
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if (recorder != null) {
                        recorder.start();
                    }
                    break;
            }
        }
    }
}
