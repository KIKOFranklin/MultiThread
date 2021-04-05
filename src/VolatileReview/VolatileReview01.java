package VolatileReview;

/**
 * 也许这个结果有点奇怪，明明isRunning已经设置为false了， 线程还没停止呢？
 * 这就要从Java内存模型（JMM）说起，这里先简单讲，虚拟机那块会详细讲的。
 * 根据JMM，Java中有一块主内存，不同的线程有自己的工作内存，同一个变量值在主内存中有一份，
 * 如果线程用到了这个变量的话，自己的工作内存中有一份一模一样的拷贝。每次进入线程从主内存中拿到变量值，
 * 每次执行完线程将变量从工作内存同步回主内存中。
 * 出现打印结果现象的原因就是主内存和工作内存中数据的不同步造成的。
 * 因为执行run()方法的时候拿到一个主内存isRunning的拷贝，
 * 而设置isRunning是在main函数中做的，换句话说 ，
 * 设置的isRunning设置的是主内存中的isRunning，
 * 更新了主内存的isRunning，线程工作内存中的isRunning没有更新，
 * 当然一直死循环了，因为对于线程来说，它的isRunning依然是true。
 * 解决这个问题很简单，给isRunning关键字加上volatile。
 * 加上了volatile的意思是，每次读取isRunning的值的时候，
 * 都先从主内存中把isRunning同步到线程的工作内存中，再当前时刻最新的isRunning。
 */
public class VolatileReview01 {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();
        mt.start();
        Thread.sleep(1000);
        mt.setRunning(false);
        System.out.println("已赋值为false");
    }
}
