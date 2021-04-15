package UnsafeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author KIKOFranklin
 * @create 2021/4/15 0015 20:06
 * @apiNote java.util.ConcurrentModificationException 并发修改异常
 * @apiNote 传统的方法ArrayList并不是线程安全的 我们有以下的解决方法
 *          1.使用vector
 *          2.使用Collections.synchronizedList(new ArrayList<>())
 *          3.使用CopyOnWriteArrayList
 *          CopyOnWrite写入并复制 COW 计算机程序设计领域的优化策略
 *          在多线程调用的情况下，先前增加的值可能会被其他线程覆盖
 *          CopyOnWrite和单词描述的一样，他的实现就是写时复制，在往集合中添加数据的时候，
 *          先拷贝存储的数组，然后添加元素到拷贝好的数组中，然后用现在的数组去替换成员变量的数组，底层使用的是lock，效率比使用synchronized的vector高
 *
 */
public class ListTest {
    public static void main(String[] args) {
        /**
         * 线程不安全的版本
         */
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list1.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list1);
            },String.valueOf(i)).start();
        }

        List<String> list2 = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list2.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list2);
            },String.valueOf(i)).start();
        }

        List<String> list3 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list3.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list3);
            },String.valueOf(i)).start();
        }
    }
}
