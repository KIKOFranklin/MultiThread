package SychronizedReview;

/**
 * @author KIKOFranklin
 * @since 2021/7/11 0011 15:53
 */
public class MyProAndCon {

    public int count = 0;

    public synchronized void produce() throws InterruptedException {
        while(count > 0){
            System.out.println("生产者停止工作！");
            wait();
            System.out.println("生产者开始工作");
        }
        ++count;
        System.out.println("商品数量：" + count);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while(count == 0){
            System.out.println("消费者停止工作！");
            wait();
            System.out.println("消费者开始工作");
        }
        --count;
        System.out.println("商品数量：" + count);
        notifyAll();
    }

    public static void startThread(Runnable r){
        new Thread(r).start();
    }


    public static void main(String[] args) {
        MyProAndCon myProAndCon = new MyProAndCon();

            startThread(() -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        myProAndCon.produce();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


            startThread(() -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        myProAndCon.consume();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

    }
}
