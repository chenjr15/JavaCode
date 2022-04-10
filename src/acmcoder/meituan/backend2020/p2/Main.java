package acmcoder.meituan.backend2020.p2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] argv) throws InterruptedException {
        ReviewEncourage.PrizePool pool = new ReviewEncourage.PrizePool();
        Scanner s = new Scanner(System.in);
        ReviewEncourage re = new ReviewEncourage(s.nextInt());

        Thread bonus = new Thread(() -> re.bonus(pool));
        Thread coupon = new Thread(() -> re.coupon(pool));
        Thread contribution = new Thread(() -> re.contribution(pool));
        List<Thread> threadList = Arrays.asList(bonus, contribution, coupon);
        Collections.shuffle(threadList);
        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }

    }
}

class ReviewEncourage {
    int n = 0;
    int i = 0;
    ReentrantLock lock = new ReentrantLock();

    public ReviewEncourage(int n) {
        this.n = n;
    }      // 构造函数,n为中奖用户数

    public void bonus(PrizePool prizePool) {
        // 仅能打印A，表示发放积分
        while (i < n) {
            // lock.lock();
            // 加锁或synchronized
            synchronized (this) {

                if (i < n && ((i % 4) == 0 || (i % 4) == 2)) {
                    prizePool.send("A");
                    ++i;

                }

            }

            // lock.unlock();
        }

    }

    public void coupon(PrizePool prizePool) {
        // 仅能打印B，表示发放优惠券

        while (i < n) {
            // lock.lock();
            synchronized (this) {

                if (i < n && (i % 4) == 1) {
                    prizePool.send("B");
                    // synchronized (this){
                    ++i;

                }


            }
            // wait();
            // lock.unlock();
        }
    }

    public void contribution(PrizePool prizePool) {
        // 仅能打印C，表示发放贡献值


        while (i < n) {
            synchronized (this) {


                if (i < n && (i % 4) == 3) {
                    prizePool.send("C");
                    // synchronized (this){
                    ++i;

                }

            }


        }
    }

    /*

        PrizePool类仅有一个send方法，实现如下：

      */
    public static class PrizePool {

        public synchronized void send(String input) {

            System.out.print(input);

        }

    }

}
