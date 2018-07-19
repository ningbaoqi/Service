### Service是什么
+ `Service`是一个一种可以在后台执行长时间运行操作而没有用户界面的应用组件；服务可以由其他组件启动如`Activity`、`Broadcast`，服务一旦被启动后，就将在后台一直运行，即使启动它的Activity、Broadcast已经被`销毁`了，也不会受到影响；可以使用`bind()`方法将`Activity`和`Service`进行绑定，然后进行数据交互；如果Activity和Service`不在同一个进程`中，也可以使用进程间通信实现数据交互；`Service`和`Broadcast`有一个共同点就是`他们都是运行在主线程中，所以不能做长时间的耗时操作`；
### Service和Thread的区别

|类别|说明|
|------|------|
|`Thread`|是程序运行的最小单元，可以使用它来完成`异步操作`；|
|`Service`|是Android的机制，当它运行的时候，如果是本地的Service，该Service是运行在`主线程`上的；Service不能进行耗时操作，如果一定要做耗时操作，应该在Service中开启子线程|
|`后台`|`Android的后台指的是它的运行完全不依赖于UI线程`，即使Activity被销毁了，或者程序被关闭了，这个服务进程仍然存在，它会在后台进行计算，数据统计，这时候Service仍然仍然可以继续运作|


