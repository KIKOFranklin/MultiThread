package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author KIKOFranklin
 * @since 2021/4/15 0015 21:45
 */
public class Test {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("召唤神龙成功"));

        for (int i = 0; i < 7; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到了" + temp + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
