### 启动远程服务
#### MainActivity
```
public class MainActivity extends AppCompatActivity {
     private Intent intent;
     private MyConnection connection;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         intent = new Intent();
         /**
          * 启动远程服务，Android5.0以上必须明确指定，所以需要设置包和action
          * */
         intent.setPackage("ningbaoqi.com.remoteservice");
         intent.setAction("remoteService");
         connection = new MyConnection();
     }
     public void start(View view) {
         startService(intent);
     }
     public void stop(View view) {
         stopService(intent);
     }
     public void bind(View view) {
         bindService(intent, connection, BIND_AUTO_CREATE);
     }
     public void unbind(View view) {
         unbindService(connection);
     }
     class MyConnection implements ServiceConnection {
         @Override
         public void onServiceConnected(ComponentName name, IBinder service) {
         }
         @Override
         public void onServiceDisconnected(ComponentName name) {
         }
     }
 }
```
#### AndroidManifest.xml
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
         package="ningbaoqi.com.remoteservice">
     <application
         android:allowBackup="true"
         android:icon="@mipmap/ic_launcher"
         android:label="@string/app_name"
         android:roundIcon="@mipmap/ic_launcher_round"
         android:supportsRtl="true"
         android:theme="@style/AppTheme">
         <activity android:name=".MainActivity">
             <intent-filter>
                 <action android:name="android.intent.action.MAIN"/>
                 <category android:name="android.intent.category.LAUNCHER"/>
             </intent-filter>
        </activity>
         <service android:name=".RemoteService">
             <intent-filter>
                 <action android:name="remoteService"/>
             </intent-filter>
         </service>
     </application>
</manifest>
```
#### RemoteService其他应用中的服务
```
public class RemoteService extends Service {
     @Nullable
     @Override
     public IBinder onBind(Intent intent) {
         Log.d("nbq", "onBind");
         return null;
     }
     @Override
     public void onCreate() {
         Log.d("nbq", "onCreate");
         super.onCreate();
     }
     @Override
     public int onStartCommand(Intent intent,int flags, int startId) {
         Log.d("nbq", "onStartCommand");
         return super.onStartCommand(intent, flags, startId);
     }
     @Override
     public void onDestroy() {
         Log.d("nbq", "onDestroy");
         super.onDestroy();
     }
     @Override
     public boolean onUnbind(Intent intent) {
         Log.d("nbq", "onUnbind");
         return super.onUnbind(intent);
     }
 }
```
