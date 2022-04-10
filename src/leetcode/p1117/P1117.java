package leetcode.p1117;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class P1117 {
    public static void main(String[] args) throws InterruptedException {

        H2O h2O = new H2O();
        Executor executor =  Executors.newFixedThreadPool(3);
        executor.execute(()-> {
            try {
                h2O.hydrogen(P1117::releaseHydrogen);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(()-> {
            try {
                h2O.hydrogen(P1117::releaseHydrogen);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(()-> {
            try {
                h2O.oxygen(P1117::releaseOxygen);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void releaseHydrogen() {
        System.out.println("H");
    }
    private static void releaseOxygen() {
        System.out.println("O");
    }
}
class H2O {

    CyclicBarrier cb = new CyclicBarrier(3);
    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        try {
            cb.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        try {
            cb.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}