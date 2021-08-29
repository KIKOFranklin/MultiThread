package future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author wangjiahao
 * @since 2021/8/29 8:40 下午
 */
public class FutureTaskByNewThread {

    private static Callable<Integer> getCallable(int param){
        return () -> param * param;
    }

    private static int multi(int param) throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(getCallable(10));
        new Thread(futureTask).start();
        while(!futureTask.isDone()){
            Thread.sleep(1000);
            System.out.println("计算未结束！");
        }
        return futureTask.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(multi(10));
    }
}
