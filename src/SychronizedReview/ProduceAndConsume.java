package SychronizedReview;


/**
 * @author KIKOFranklin
 * @create 2021/4/15 0015 14:36
 * wait()使当前线程阻塞，前提是 必须先获得锁，一般配合synchronized 关键字使用，即，一般在synchronized 同步代码块里使用 wait()、notify/notifyAll() 方法。
 * 由于 wait()、notify/notifyAll() 在synchronized 代码块执行，说明当前线程一定是获取了锁的。
 * 当线程执行wait()方法时候，会释放当前的锁，然后让出CPU，进入等待状态。
 * 只有当 notify/notifyAll() 被执行时候，才会唤醒一个或多个正处于等待状态的线程，然后继续往下执行，直到执行完synchronized 代码块的代码或是中途遇到wait() ，再次释放锁。
 * 也就是说，notify/notifyAll() 的执行只是唤醒沉睡的线程，而不会立即释放锁，锁的释放要看代码块的具体执行情况。所以在编程中，尽量在使用了notify/notifyAll() 后立即退出临界区，以唤醒其他线程让其获得锁
 * notify方法只唤醒一个等待（对象的）线程并使该线程开始执行。所以如果有多个线程等待一个对象，这个方法只会唤醒其中一个线程，选择哪个线程取决于操作系统对多线程管理的实现。
 * notifyAll 会唤醒所有等待(对象的)线程，尽管哪一个线程将会第一个处理取决于操作系统的实现。如果当前情况下有多个线程需要被唤醒，推荐使用notifyAll 方法。
 * 要注意，notify唤醒沉睡的线程后，线程会接着上次的执行继续往下执行。所以在进行条件判断时候，可以先把 wait 语句忽略不计来进行考虑；
 * 显然，要确保程序一定要执行，并且要保证程序直到满足一定的条件再执行，要使用while进行等待，直到满足条件才继续往下执行。
 * notifyAll之后 所有线程会去争抢当前对象锁 争抢到锁的线程继续执行相应代码 而没有争抢到的则继续在线程池等待
 *
 */
public class ProduceAndConsume {

    private Integer count = 0;


    public synchronized void produce() throws InterruptedException {
        while(count != 0){
            System.out.println("生产者停止工作");
            wait();
            System.out.println("生产者开始工作");
        }
        count++;
        System.out.println(count);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
       while(count == 0){
           System.out.println("消费者停止工作");
           wait();
           System.out.println("消费者开始工作");
       }
       count--;
       System.out.println(count);
       notifyAll();
    }

    public static void main(String[] args) {
        ProduceAndConsume bean = new ProduceAndConsume();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    bean.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    bean.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    bean.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    bean.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
