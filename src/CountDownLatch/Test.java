package CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author KIKOFranklin
 * @create 2021/4/15 0015 21:41
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        //新建6秒计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"go out");
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();//等待计数器归零 再向下执行

        System.out.println("close door");
    }
}
