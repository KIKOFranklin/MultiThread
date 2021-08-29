package future;

import java.sql.Connection;
import java.util.Calendar;
import java.util.concurrent.*;

/**
 * @author wangjiahao
 * @since 2021/8/29 9:54 下午
 */
public class GenerateConnectionByFutureTask {

    private static ConcurrentHashMap<String, FutureTask<Connection>> connectionPool = new ConcurrentHashMap<>();

    public static Connection getConnection(final String key) throws ExecutionException, InterruptedException {
        FutureTask<Connection> futureTask = connectionPool.get(key);
        if(futureTask != null){
            System.out.println("使用futureTask已经生成好的连接对象");
            return futureTask.get();
        } else {
            Callable<Connection> callable = () -> createConnection();
            FutureTask<Connection> newTask = new FutureTask<>(callable);
            futureTask = connectionPool.putIfAbsent(key, newTask);
            if(futureTask == null){
                futureTask = newTask;https://github.com/KIKOFranklin/MultiThread.git
                new Thread(futureTask).start();
//                futureTask.run();
            }
            return futureTask.get();
        }
    }

    /**
     * 根据自己的业务需求定制Connection对象
     * @return Connection
     */
    private static Connection createConnection(){
        System.out.println("使用createConnection获取连接对象");
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        getConnection("1");
        getConnection("1");
        getConnection("1");
    }
}
