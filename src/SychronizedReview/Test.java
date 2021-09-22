package SychronizedReview;

/**
 * @author wangjiahao
 * @since 2021/9/18 8:06 下午
 */
public class Test {

    private int ticket = 0;

    private synchronized void produce() throws InterruptedException {
        while (ticket != 0) {
//            System.out.println("生产者停止工作！");
            wait();
//            System.out.println("生产者开始工作！");
        }
        ticket++;
        System.out.println("p当前还剩余:" + ticket);
        notifyAll();
    }

    private synchronized void consume() throws InterruptedException {
        while (ticket == 0) {
//            System.out.println("消费者停止工作!");
            wait();
//            System.out.println("消费者开始工作！");
        }
        ticket--;
        System.out.println("c当前还剩余:" + ticket);
        notifyAll();
    }

    public static void main(String[] args) {

        Test test = new Test();

        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    test.produce();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                for (int j = 0; j < 20; j++) {
                    test.consume();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
