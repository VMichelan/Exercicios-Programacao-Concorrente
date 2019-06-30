import java.util.concurrent.Semaphore;

public class ThreadA extends Thread {

    Semaphore semaphoreA;
    Semaphore semaphoreB;

    ThreadA(Semaphore semaphoreA, Semaphore semaphoreB) {
        this.semaphoreA = semaphoreA;
        this.semaphoreB = semaphoreB;
    }

    @Override
    public void run() {
        try {
            System.out.println("Running 1.1");
            //wait 2.1
            this.semaphoreB.release();
            this.semaphoreA.acquire();
            System.out.println("Running 1.2");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}