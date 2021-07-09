package SychronizedReview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author KIKOFranklin
 * @create 2021/4/15 0015 19:56
 * 生成多个condition对象 通过condition对象实现精准唤醒
 */
public class LockProdAndCons {

    private Integer count = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition1 = lock.newCondition();


    public void produce() throws InterruptedException {

        lock.lock();

        try {
            while(count != 0){
                System.out.println("生产者停止工作");
                condition.await();
                System.out.println("生产者开始工作");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        count++;
        System.out.println(count);
        condition1.signal();
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while(count == 0){
                System.out.println("消费者停止工作");
                condition1.await();
                System.out.println("消费者开始工作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        count--;
        System.out.println(count);
        condition.signal();
    }

    public static void main(String[] args) {
        ProduceAndConsume bean = new ProduceAndConsume();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    bean.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    bean.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    bean.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    bean.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
