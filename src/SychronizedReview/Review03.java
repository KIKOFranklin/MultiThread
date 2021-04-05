package SychronizedReview;
/**
 * synchronized关键字：
 *                  类锁:
 *                  public static synchronized void function(){
 *
 *                  } 或
 *                  synchronized(xxx.class){
 *                      //当前类的锁
 *                  }
 *                  ------------------------------------------------
 *                  对象锁:
 *                  public synchronized void function(){
 *
 *                  } 或
 *                  synchronized(this/对象监听器（同一实体当中的实例变量）){
 *                      //当前对象的锁
 *                  }
 *
 *                  结论:同一线程访问类锁和对象锁时 线程不会阻塞
 *
 *                  其实类锁只是一个概念上的东西，并不是真实存在的，它只是用来帮助我们理解锁定实例方法和静态方法的区别的。
 *
 *                  java类可能会有很多个对象，但是只有1个Class对象，也就是说类的不同实例之间共享该类的Class对象。
 *
 *                  Class对象其实也仅仅是1个java对象，只不过有点特殊而已。
 *
 *                  由于每个java对象都有1个互斥锁，而类的静态方法是需要Class对象。
 *
 *                  所以所谓的类锁，不过是Class对象的锁而已。
 *
 *                  获取类的Class对象有好几种，最简单的就是MyClass.class的方式。
 */
public class Review03 {
    /**
     * 电影票数.
     */
    public static Integer tickets = 100;

    /**
     * 类锁方法.
     */
    public static synchronized void staticfunction(Integer tickets){
        /*消费次数*/
        int consumeCount = 1;
        /**
         * 循环10次 每次票数减1.
         */
        for(int i = 0; i < 100; i++){
            tickets = tickets - consumeCount;
            System.out.println("用户"+Thread.currentThread().getName()+"购买了一张票,总票数还剩:"+tickets);
        }
    }

    /**
     * 对象锁方法.
     */
    public synchronized void function(Integer tickets){
        /*消费次数*/
        int consumeCount = 1;
        /**
         * 循环10次 每次票数减1.
         */
        for(int i = 0; i < 100; i++){
            tickets = tickets - consumeCount;
            System.out.println("用户"+Thread.currentThread().getName()+"购买了一张票,总票数还剩:"+tickets);
        }
    }

    public static void main(String[] args) {
        Review03 review03 = new Review03();
        new Thread(() -> {
            review03.function(Review03.tickets);
        },"王家豪").start();

        new Thread(() -> {
            Review03.staticfunction(Review03.tickets);
        },"徐鹏飞").start();
    }
}
