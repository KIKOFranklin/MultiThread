package Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author KIKOFranklin
 * @create 2021/4/15 0015 21:57
 */
public class Test {
    public static void main(String[] args) {
        //线程数量：最多三个线程
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire(); //获取线程位置 如果已满 且是队列等待 先行acquire的线程位置靠前 遵循FIFO的规则
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//腾出线程位置 并唤醒顺序等待线程
                }
            }, String.valueOf(i)).start();
        }
        System.out.println("for循环执行完毕");
    }
}
