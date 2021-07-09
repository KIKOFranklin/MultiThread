package Unsafe2Safe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author KIKOFranklin
 * @create 2021/4/15 0015 21:12
 * @apiNote 使用ConcurrentHashMap或者Collections.synchronizedMap 解决Map并发问题
 */
public class MapTest {
    public static void main(String[] args) {
        /**
         * 线程不安全
         */
        Map<String, String> map1 = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                map1.put(UUID.randomUUID().toString().substring(0, 5), "1");
                System.out.println(map1);
            }).start();
        }

        Map<String, String> map2 = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                map2.put(UUID.randomUUID().toString().substring(0, 5), "2");
                System.out.println(map2);
            }).start();
        }

        Map<String, String> map3 = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                map3.put(UUID.randomUUID().toString().substring(0, 5), "3");
                System.out.println(map3);
            }).start();
        }
    }
}
