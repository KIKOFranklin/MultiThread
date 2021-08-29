package future;

import java.util.concurrent.*;

/**
 * @author wangjiahao
 * @since 2021/8/29 6:53 下午
 */
public class FutureTaskByThreadPool {

    public static Future<Integer> calculate(int param){
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        return threadPool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ": 计算任务开始");
            Thread.sleep(1000);
            return param * param;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Integer> result1 = FutureTaskByThreadPool.calculate(10);
        while(!result1.isDone()){
            Thread.sleep(1000);
            System.out.println("计算还没结束");
        }
        System.out.println("计算结果为:" + result1.get());
    }
}
