package leetcode.p1226;

import java.util.concurrent.Semaphore;

public class P1226 {
    public static void main(String[] args) {

    }
}
class DiningPhilosophers {
    Semaphore maxp = new Semaphore(4);
    Semaphore[] chopsticks = new Semaphore[]{
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
    };
    public DiningPhilosophers() {

    }
    public void  pickLeftFork(){

    }

    public void  pickRightFork(){

    }

    public void  eat(){

    }
    public void  putLeftFork(){

    }
    public void  putRightFork(){

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        maxp.acquire();

        chopsticks[philosopher].acquire();
        pickLeftFork.run();

        chopsticks[(philosopher+1)%5].acquire();
        pickRightFork.run();

        eat.run();
        chopsticks[philosopher].release();
        putRightFork.run();

        chopsticks[(philosopher+1)%5].release();
        putLeftFork.run();

        maxp.release();

    }
}