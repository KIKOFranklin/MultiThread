package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wangjiahao
 * @since 2021/9/22 8:50 下午
 * @apiNote 阻塞超时后，cyclicBarrier.isBroken()会返回true，此时需要reset这个barrier才能继续使用它，
 *          reset并不是重设等待线程数量，barrier中的线程数量始终是一个循环，reset是针对broken情况设计的。
 */
public class Review {

    public CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
        System.out.println("Final task is executed by " + Thread.currentThread().getName());
    });

    public static void main(String[] args) throws Exception{

        Review review = new Review();

        for (int i = 0; i < 5; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("当前线程等待数：" + review.cyclicBarrier.getNumberWaiting());
                    review.cyclicBarrier.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                if(review.cyclicBarrier.isBroken()){
                    System.out.println("阻塞超时，屏障被打破了！");
                }
                System.out.println("业务代码"+ index +"----->");
            }).start();
            Thread.sleep(1500);
        }

        review.cyclicBarrier.reset();

        for (int i = 0; i < 5; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("当前线程等待数：" + review.cyclicBarrier.getNumberWaiting());
                    review.cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("业务代码"+ index +"----->");
            }).start();
        }
    }
}
