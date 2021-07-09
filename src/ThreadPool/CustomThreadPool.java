package ThreadPool;

import java.util.concurrent.*;

/**
 * @author KIKOFranklin
 * @create 2021/4/17 0017 20:58
 */
public class CustomThreadPool {
    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        /**
         * 使用线程池创建
         */
        try {
            for (int i = 0; i < 5; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
