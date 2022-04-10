package leetcode.p1114;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class P1114 {
    public static void main(String[] args) throws InterruptedException{
        Foo foo = new Foo();
        Thread first = new Thread(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread second = new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread third = new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        List<Thread> threads = Arrays.asList(first, second, third);
        Collections.shuffle(threads);
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

    }
}
class Foo {
volatile
    // Semaphore first = new Semaphore(0);
    Semaphore second = new Semaphore(0);
    Semaphore third = new Semaphore(0);

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // third.acquire();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        second.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        third.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.acquire();

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();

        // first.acquire();
    }
}