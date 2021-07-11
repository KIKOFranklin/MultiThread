package ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author KIKOFranklin
 * @create 2021/4/17 0017 14:44
 * @apiNote 读写锁 读的时候可以被多个线程读 写只能由一个线程写
 *          需要注意的是 当其中一个线程先获取到写锁时 读锁会同时被阻塞
 *          写锁中的unlock也只是唤醒其他想获取写锁的线程来竞争写锁 只有当获取写锁的线程全部死亡后 读锁才能够被获取到
 *          反之 当其中一个线程先获取到读锁时 写锁也会同时被阻塞 只有当读锁完全释放时 写锁才能够被获取到 读锁是共享锁 线程之间可以一起使用
 *          需要再次强调的是 虽然有读锁这个概念 但因其是共享锁 线程执行仍是并发执行的 并没有出现等待的状况
 *          而写锁则类似于synchronized 是一把重量级锁 同样也是一把独占锁
 *
 *          思考：不加锁的情况下 线程也是并发执行的 为什么还需要加一把读锁？
 *               因为不加读锁，在写入的过程中会发生读，会造成数据不一致的情况，加了读锁，会与写锁互斥，保证了先写后读！！
 *
 *
 *          独占锁（写）
 *          共享锁（读）
 *          读 - 读 可以共存
 *          读 - 写 不能
 *          写 - 写 不能
 *
 *          读写锁 是 互斥锁
 */
public class Test {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();

        for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.put(String.valueOf(temp), temp);

            }).start();
        }

        for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.get(String.valueOf(temp));
            }).start();
        }

    }
}

/**
 * 自定义缓存
 */
class MyCache{
    private volatile Map<String, Object> cache = new HashMap<>();
    //读
    public Object get(String name){
        System.out.println(Thread.currentThread().getName()+"读取");
        Object object = cache.get(name);
        System.out.println("读取成功!");
        return object;
    }
    //写
    public void put(String name, Object object){
        System.out.println(Thread.currentThread().getName()+"写入");
        cache.put(name, object);
        System.out.println("写入成功!");
    }
}


/**
 * 自定义缓存加锁版
 */
class MyCacheLock{
    private volatile Map<String, Object> cache = new HashMap<>();

    //读写锁 写只能由一个线程写 读可以由多个线程读
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读
    public Object get(String name){
        readWriteLock.readLock().lock();
        Object object = null;
        try {
            System.out.println(Thread.currentThread().getName()+"读取");
            object = cache.get(name);
            System.out.println("读取成功!"+object.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return object;
    }
    //写
    public void put(String name, Object object){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入");
            cache.put(name, object);
            System.out.println("写入成功!"+object.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
