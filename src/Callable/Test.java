package Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author KIKOFranklin
 * @create 2021/4/15 0015 21:20
 */
public class Test {
    public static void main(String[] args) {
        /*
            FutureTask的构造方法可以将Callable接口传递进来进行构造
            同时Thread的构造方法只能传runnable接口 但是FutureTask实现了runnable接口 故可以传递构造
         */
        Mythread mythread = new Mythread();
        FutureTask<String> futureTask = new FutureTask<>(mythread);
        new Thread(futureTask,"A").start();
    }
}

class Mythread implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "HelloWorld";
    }
}
