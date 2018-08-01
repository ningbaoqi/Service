### 使用AIDL技术调用远程服务
#### Client：src/main/aidl/com/ningbaoqi/aidl/IProxy.java
```
interface IProxy {
     /**
      * Demonstrates some basic types that you can use as parameters
      * and return values in AIDL.
      */
     void doSome();
 }
```
#### Client：src/main/java/ningbaoqi/com/client/MainActivity.java
```
public class MainActivity extends AppCompatActivity {
     private Intent intent;
     private MyContent content;
     IProxy proxy;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         intent = new Intent();
         intent.setPackage("ningbaoqi.com.remoteservice2");
         intent.setAction("remote");
         content = new MyContent();
     }
     public void bind(View view) {
         bindService(intent, content, BIND_AUTO_CREATE);
     }
     public void unbind(View view) {
         unbindService(content);
     }
     public void call(View view) throws RemoteException {
         proxy.doSome();
     }
     class MyContent implements ServiceConnection {
        @Override
         public void onServiceConnected(ComponentName name, IBinder service) {
             proxy = IProxy.Stub.asInterface(service);
         }
         @Override
         public void onServiceDisconnected(ComponentName name) {
         }
     }
 }
```
#### remoteservice2：src/main/AndroidManifest.xml
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="ningbaoqi.com.remoteservice2">
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
                <action android:name="remote"/>
            </intent-filter>
        </service>
    </application>
</manifest>
```
#### remoteservice2：src/main/aidl/com/ningbaoqi/aidl/IProxy.aidl
```
interface IProxy {
     /**
      * Demonstrates some basic types that you can use as parameters
      * and return values in AIDL.
      */
     void doSome();
 }
```
#### remoteservice2：src/main/java/ningbaoqi/com/remoteservice2/RemoteService.java
```
public class RemoteService extends Service {
     @Nullable
     @Override
     public IBinder onBind(Intent intent) {
         return new ProxyNative();
     }
 
     @Override
     public void onCreate() {
 
     }
 
     @Override
     public boolean onUnbind(Intent intent) {
         return super.onUnbind(intent);
     }
     private void doSomething(){
         Log.d("nbq", "caonimalianggefashabi");
     }
     class ProxyNative extends IProxy.Stub {
 
         @Override
         public void doSome() throws RemoteException {
              RemoteService.this.doSomething();
         }
     }
 }
```
