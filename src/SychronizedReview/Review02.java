package SychronizedReview;

public class Review02 {
    /**
     * Java还支持对“任意对象”作为“对象监视器”来实现同步的功能。
     * 这个“任意对象”大多数是实例变量及方法的参数，使用格式为synchronized(非this对象)
     * 如果一个类中有很多个synchronized方法，这时虽然能实现同步，但会受到阻塞，所以影响消息；
     * 但如果使用同步代码块锁非this对象，则synchronized(非this)代码块的程序与同步方法是异步的。
     * 不与其他锁this同步方法争抢this锁，则可以大大提高运行效率。
     */
    private String lock = new String();

    public void doTimeConsumingTask() throws InterruptedException {
        System.out.println("当前线程"+Thread.currentThread().getName()+"正在处理耗时任务,其内容不需要同步");
        Thread.sleep(2000);
    /**
     * 使用synchronized(非this对象x)同步代码块 进行同步操作时，对象监视器必须是同一个对象。
     * 如果不是同一个对象监视器，运行的结果就是异步调用了，就会交叉运行。
     */
        //        String lock = new String();


        synchronized (lock){
            System.out.println("线程"+Thread.currentThread().getName()+"正在处理同步代码块，对其同步变量进行操作");
            Thread.sleep(1000);
        }
        System.out.println("线程"+Thread.currentThread().getName()+"任务处理完毕!");

    }

    public synchronized void doThisJob(){
        System.out.println(Thread.currentThread().getName() + "I'm working");
    }

    public static void startThread(Runnable r){
        new Thread(r).start();
    }


    public static void main(String[] args) {
        Review02 review02 = new Review02();
        startThread(() -> {
            try {
                review02.doTimeConsumingTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        startThread(() -> {
            review02.doThisJob();
        });
    }
}
