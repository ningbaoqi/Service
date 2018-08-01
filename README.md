### Service是什么
+ `Service`是一个一种可以在后台执行长时间运行操作而没有用户界面的应用组件；服务可以由其他组件启动如`Activity`、`Broadcast`，服务一旦被启动后，就将在后台一直运行，即使启动它的Activity、Broadcast已经被`销毁`了，也不会受到影响；可以使用`bind()`方法将`Activity`和`Service`进行绑定，然后进行数据交互；如果Activity和Service`不在同一个进程`中，也可以使用进程间通信实现数据交互；`Service`和`Broadcast`有一个共同点就是`他们都是运行在主线程中，所以不能做长时间的耗时操作`；

### [Service生命周期](https://github.com/ningbaoqi/Service/blob/master/README-life.md)

### Service和Thread的区别

|类别|说明|
|------|------|
|`Thread`|是程序运行的最小单元，可以使用它来完成`异步操作`；|
|`Service`|是Android的机制，当它运行的时候，如果是本地的Service，该Service是运行在`主线程`上的；Service不能进行耗时操作，如果一定要做耗时操作，应该在Service中开启子线程|
|`后台`|`Android的后台指的是它的运行完全不依赖于UI线程`，即使Activity被销毁了，或者程序被关闭了，这个服务进程仍然存在，它会在后台进行计算，数据统计，这时候Service仍然仍然可以继续运作|

### 启动Service方法方式
#### startService()
+ 使用该方法该服务将会在后台无限运行，服务并不是运行在一个独立的进程当中的，而是依赖于创建服务时所在的应用程序进程，当某个应用程序进程被杀掉，所有依赖于该进程的服务也会停止运行，但是在`onStartCommand()`的返回值中有一个`START_STICKY`标志位用来在服务所在进程销毁之后，重新创建该进程，并启动服务调用`onStartCommand`方法；startService()该方法启动的服务所在的进程属于服务进程，Activity一旦启动服务，服务就跟Activity没有关系了,即便Activity退出了，服务也仍然进行,Activity与服务无法进行通信，无法交换数据；

|生命周期方法|说明|
|------|------|
|`onCreate`|服务被创建时调用，多次启动一个已有的Service不会调用onCreate|
|`onStartCommand`|创建之后启动时调用这个方法，每次启动都会调用这个方法|
|`onDestroy`|服务销毁时调用|

+ [显式启动服务停止服务](https://github.com/ningbaoqi/Service/commit/04e4d193845e9820daea3f6ad7920851a7c70140)

#### bindService()
+ bindService()该方法启动的服务所在的进程不属于服务进程，Activity与服务建立连接，Activity一旦死亡，服务也会死亡，Activity可以与服务进行通信，并可以交换数据；
+ [使用bind服务例子](https://github.com/ningbaoqi/Service/commit/48b879e929e2a1b00da15fda79a34962eda64fcc)

### 使用bind服务并且使activity调用service方法
+ [使用bind服务并且使activity调用service方法](https://github.com/ningbaoqi/Service/commit/6f98f36213c7d56dc4e95034c03897da36fc176c)

### 使用服务实现电话录音机
+ [使用服务实现电话录音机](https://github.com/ningbaoqi/Service/commit/a7e26f48248f0084d0e388da986914cb63b8ba0d)

### [启动远程服务](https://github.com/ningbaoqi/Service/blob/master/README-remote.md)

### [使用AIDL技术调用远程服务](https://github.com/ningbaoqi/Service/blob/master/README-aidl.md)
