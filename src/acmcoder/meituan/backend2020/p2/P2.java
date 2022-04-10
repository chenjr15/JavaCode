package acmcoder.meituan.backend2020.p2;

import java.util.*;

public class P2 {
    public static void main(String[] argv) throws InterruptedException {
        int v =((int) ( Math.random() * 100 ))% 20;
        Scanner s = new Scanner(System.in);
        v= s.nextInt();
        for (int i = 0; i < v; i++) {
            System.out.println("Test for "+i);
            test(i);
        }
    }

    private static void test(int n) throws InterruptedException {
        ReviewEncourage re = new ReviewEncourage(n);
        Pool pool = new Pool(n);
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
        System.out.println("");
        boolean check = pool.check();
    }

    static class Pool extends ReviewEncourage.PrizePool {
        private final int n;
        List<String> list = new ArrayList<>();

        public Pool(int n) {
            this.n = n;
        }

        @Override
        public synchronized void send(String input) {
            this.list.add(input);
            super.send(input);
        }

        public boolean check() {
            if (n != this.list.size()) {
                System.out.printf("Wrong size %d != %d\n", list.size(), n);

                return false;
            }
            for (int i = 0; i < list.size(); i++) {
                String correct = "";
                if ((i & 1) == 0) {
                    correct = "A";

                } else if (i % 4 == 1) {
                    correct = "B";

                } else if (i % 4 == 3) {
                    correct = "C";

                }else {
                    correct= "Not match "+i;
                }

                // 奇数
                if (!Objects.equals(list.get(i), correct)) {
                    System.out.printf("Wrong at %d :%s != %s\n", i, list.get(i), correct);
                    return false;
                }
            }
            System.out.println("PASS!");
            return true;

        }
    }
}
