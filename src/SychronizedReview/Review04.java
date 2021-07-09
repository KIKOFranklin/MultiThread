package SychronizedReview;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  synchronized和lock的区别
 *  1、synchronized是Java内置的关键字 Lock是一个Java类
 *  2、synchronized无法判断锁的状态 Lock可以判断是否获取到了锁 内部默认实现是非公平锁 NonfairSync()
 *  3、synchronized会自动释放锁、Lock锁必须要手动释放。如果不释放锁 会发生死锁
 *  4、synchronized中线程1阻塞后 线程2会一直等待 Lock锁就不一定会等下去了lock.tryLock()
 *  5、synchronized可重入锁、不可以中断、非公平锁 Lock可重入锁 可以判断锁 可以设置（非）公平锁
 *  6、synchronized适合锁少量的代码块 Lock适合锁大量的同步代码
 */
public class Review04 {
    /**
     * 电影票数.
     */
    public static Integer tickets = 100;

    /**
     * 类锁方法.
     */
    public static synchronized void staticfunction(Integer tickets){
        /*消费次数*/
        int consumeCount = 1;
        /**
         * 循环10次 每次票数减1.
         */
        for(int i = 0; i < 100; i++){
            tickets = tickets - consumeCount;
            System.out.println("用户"+Thread.currentThread().getName()+"购买了一张票,总票数还剩:"+tickets);
        }
    }

    /**
     * 对象锁方法.
     */
    public void function(Integer tickets){

        Lock lock = new ReentrantLock();
        //上锁
        lock.lock();

        //尝试获取锁
        lock.tryLock();

        try{
            /*消费次数*/
            int consumeCount = 1;
            /**
             * 循环10次 每次票数减1.
             */
            for(int i = 0; i < 100; i++){
                tickets = tickets - consumeCount;
                System.out.println("用户"+Thread.currentThread().getName()+"购买了一张票,总票数还剩:"+tickets);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //解锁
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Review04 review04 = new Review04();
        new Thread(() -> {
            review04.function(Review03.tickets);
        },"王家豪").start();

        new Thread(() -> {
            review04.function(Review03.tickets);
        },"徐鹏飞").start();
    }
}
