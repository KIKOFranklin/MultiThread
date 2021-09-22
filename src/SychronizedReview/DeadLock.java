package SychronizedReview;

/**
 * @author wangjiahao
 * @since 2021/9/18 8:37 下午
 */
public class DeadLock {
    private Object lock1 = new Object();

    private Object lock2 = new Object();

    public static void main(String[] args) {

        DeadLock deadLock = new DeadLock();

        new Thread( () -> {
            synchronized (deadLock.lock1){
                System.out.println("获取到lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (deadLock.lock2){
                    System.out.println("获取到lock2");
                }
            }
        }).start();

        new Thread( () -> {
            synchronized (deadLock.lock2){
                System.out.println("获取到lock2");
                synchronized (deadLock.lock1){
                    System.out.println("获取到lock1");
                }
            }
        }).start();
    }
}
