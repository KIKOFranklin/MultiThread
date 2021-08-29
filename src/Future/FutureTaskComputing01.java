package future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wangjiahao
 * @since 2021/8/29 9:08 下午
 */
public class FutureTaskComputing01 {

    private static void BigComputeByFutureTask() throws ExecutionException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        FutureTaskComputing01 futureTaskComputing = new FutureTaskComputing01();
        List<Future<Integer>> taskList = new ArrayList<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            FutureTask<Integer> futureTask = new FutureTask<>(futureTaskComputing.new ComputeTask());
            taskList.add(futureTask);
            threadPool.submit(futureTask);
        }
        for (Future<Integer> el: taskList) {
            System.out.println("计算结果：" + el.get());
        }
        threadPool.shutdown();
        Long endTime = System.currentTimeMillis();
        System.out.println("子任务方法执行的时间为:" + (endTime - startTime));

    }

    private static void BigComputeOrdinary() throws ExecutionException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        final CountDownLatch latch = new CountDownLatch(5);
        new Thread(() -> {
            for (int j = 0; j < 5; j++) {
                int result = 0;
                for (int i = 0; i < 100; i++) {
                    result += i;
                }
                System.out.println("计算结果：" + result);
                latch.countDown();
            }
        }).start();
        latch.await();
        Long endTime = System.currentTimeMillis();
        System.out.println("普通方法执行的时间为:" + (endTime - startTime));
    }




    private class ComputeTask implements Callable<Integer>{



        private int result;

        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 100; ++i) {
                result += i;
            }
            return result;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BigComputeByFutureTask();
//        BigComputeOrdinary();
    }
}
