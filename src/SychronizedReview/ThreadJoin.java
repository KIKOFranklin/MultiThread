package SychronizedReview;

/**
 * @author wangjiahao
 * @since 2021/9/18 9:06 下午
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread( () -> {
            try {
                Thread.sleep(3000);
                System.out.println("子线程执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        System.out.println("主线程执行完毕");
    }
}
