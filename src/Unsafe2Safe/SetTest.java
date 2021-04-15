package UnsafeList;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author KIKOFranklin
 * @create 2021/4/15 0015 20:56
 * @apiNote 传统的方法ArrayList并不是线程安全的 我们有以下的解决方法
 *          1.使用Collections.synchronizedSet(new HashSet<>())
 *          2.使用CopyOnWriteArraySet
 */
public class SetTest {
    public static void main(String[] args) {
        /**
         * 线程不安全
         */
        Set<String> set1 = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                set1.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set1);
            }).start();
        }

        Set<String> set2 = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                set2.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set2);
            }).start();
        }

        Set<String> set3 = new CopyOnWriteArraySet();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                set3.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set3);
            }).start();
        }
    }
}
