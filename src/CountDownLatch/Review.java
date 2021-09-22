package CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wangjiahao
 * @since 2021/9/22 8:32 下午
 * @apiNote CountDownLatch的countDown方法会对所有的阻塞线程生效
 */
public class Review {

    public CountDownLatch latch = new CountDownLatch(5);

    public static void main(String[] args) {
        Review review = new Review();
        new Thread(() -> {
            System.out.println("第一个线程启动了");
            try {
                review.latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一个线程执行结束");
        }).start();

        new Thread(() -> {
            System.out.println("第二个线程启动了");
            try {
                review.latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二个线程执行结束");
        }).start();

        new Thread(() -> {
            System.out.println("第三个线程启动了");
            for (int i = 0; i < 3; i++) {
                System.out.println("倒计时：" + (5 - i));
                review.latch.countDown();
            }
            System.out.println("第三个线程执行结束");
        }).start();
    }
}
