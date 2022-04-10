package thread.sync;

import sun.awt.windows.ThemeReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Signal {
    private static volatile int signal = 0;

    static class CounterThread implements Runnable {
        private final int order;
        private static int times;
        private static int total=0;
        public CounterThread( ){
            this.order = total;
            total++;

        }
        public CounterThread( int times){
            // 必须显式调用无参构造器
            this();
            CounterThread.times =  times;


        }
        @Override
        public void run() {
            while (signal < times) {
                if (signal % total == order) {
                    System.out.println("thread"+order+": " + signal);
                    synchronized (this) {
                        signal++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new CounterThread(10));
        Thread t2= new Thread(new CounterThread(10));
        Thread t3 = new Thread(new CounterThread(10));
        List<Thread> threads = Arrays.asList(t1, t2, t3);
        Collections.shuffle(threads);
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }


    }
}
