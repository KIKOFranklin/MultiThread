package future;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wangjiahao
 * @since 2021/8/29 9:08 下午
 */
public class FutureTaskComputing {
    private static void BigCompute() throws ExecutionException, InterruptedException {

        FutureTaskComputing futureTaskComputing = new FutureTaskComputing();
        List<Future<Integer>> taskList = new ArrayList<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            Future<Integer> task = threadPool.submit(futureTaskComputing.new ComputeTask());
            taskList.add(task);
        }

        for (Future<Integer> el: taskList) {
            Thread.sleep(1000);
            System.out.println("计算结果：" + el.get());
        }

        threadPool.shutdown();
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
        BigCompute();
    }
}
