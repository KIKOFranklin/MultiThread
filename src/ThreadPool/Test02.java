package ThreadPool;

import java.util.concurrent.*;

/**
 * @author KIKOFranklin
 * @since 2021/7/11 0011 14:55
 */
public class Test02 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(
                    () -> {
                        System.out.println(Thread.currentThread().getName()  + ": I'm working");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        threadPool.shutdownNow();
    }
}
