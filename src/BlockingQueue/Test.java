package BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author KIKOFranklin
 * @create 2021/4/17 0017 15:27
 * @apiNote ArrayBlockingQueue代码见笔记阻塞队列
 */
public class Test {
    public static void main(String[] args) {

        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "存入成功!");
                Thread.sleep(2000);
                synchronousQueue.put("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(synchronousQueue.take());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "读取成功!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
