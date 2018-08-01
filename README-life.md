### Service生命周期
+ 因为启动方式不同则有不同的`生命周期`，如下为两种方式启动Service的生命周期：

image

#### 使用startService()方法启动服务的生命周期

|生命周期方法|说明|
|------|------|
|onCreate|服务被创建时调用，多次启动一个已有的Service不会调用onCreate|
|onStartCommand|创建之后启动时调用这个方法，每次启动都会调用这个方法|
|onDestroy|服务销毁时调用|

#### 服务的混合调用
+ Service生命周期还有一种特殊的情形：如果Service已由某个客户端通过`startService()`方法启动了，接下来其他客户端调用了`bindService()`方法绑定该Service后，再调用`unbindService()`方法解除绑定，最后又调用了`bindService()`方法再次绑定到Service，这个过程所触发的生命周期方法如下：`onCreate()->onStartCommand()->onBind()->onUnbind()（重写该方法时返回了true）->onRebind()`；但是并没有发现Service回调了onDestroy()方法，这是因为该Service并不是由Activity通过bindService()方法启动的（该Service事先已由Activity通过startService()方法启动了）因此当Activity调用`unBindService()`方法取消与该Service绑定时，该Service也不会终止；由此可见，当Activity调用bindService()绑定一个已经启动的Service时，系统只是把Service内部IBinder对象传给了Activity，并不会把该Service生命周期完全绑定到该Activity，因而当Activity调用unBindService()方法取消与该Service的绑定时，也只是切断了该Activity与Service之间的关联，并不能停止该Service组件；
##### 服务混合调用例子

```
/**
 * 服务的混合调用顺序 开始--->绑定----->解绑---->停止 服务混合调用，为了把服务所在进程变成服务进程
 */
 startService(intent);
 /**
 * 为了拿到中间人对象
 * */
 bindService(intent , connection , BIND_AUTO_CREATE);
```

#### startService和bindService区别
+ 服务并不是运行在一个独立的进程当中的，而是依赖于创建服务时所在的应用程序进程，当某个应用程序进程被杀掉，所有依赖于该进程的服务也会停止运行;startService()该方法启动的服务所在的进程属于服务进程，Activity一旦启动服务，服务就跟Activity没有关系了,即便Activity退出了，服务也仍然进行,Activity与服务无法进行通信，无法交换数据;bindService()该方法启动的服务所在的进程不属于服务进程，Activity与服务建立连接，Activity一旦死亡，服务也会死亡，Activity可以与服务进行通信，并可以交换数据；
### 本地服务和远程服务的区别

|服务|说明|
|------|------|
|本地服务|指的是服务和启动服务的Activity在同一个进程中|
|远程服务|指的是服务和启动服务的Activity不在同一个进程中|

### 使用aidl进行进程间通信
+ `aidl文件`为了进行`进程间通信`的；首先创建一个`aidl文件夹`，然后在该文件夹下创建`aidl文件`，并且写入你想写的方法，注意方法不能有限定符，因为默认是`public`的，然后`make project`下项目，就在build文件夹下出现了该`aidl文件`对应的`java文件`；`注意包名一定和远程服务该文件的包名一致`；
