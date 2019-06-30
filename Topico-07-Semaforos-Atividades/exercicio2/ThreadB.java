
import java.util.concurrent.Semaphore;

public class ThreadB extends Thread {

    Semaphore semaphoreA;
    Semaphore semaphoreB;

    ThreadB(Semaphore semaphoreA, Semaphore semaphoreB) {
        this.semaphoreA = semaphoreA;
        this.semaphoreB = semaphoreB;
    }

    @Override
    public void run() {
        try {
            System.out.println("Running 2.1");
                //wait 1.1
            this.semaphoreA.release();
            this.semaphoreB.acquire();
            System.out.println("Running 2.2");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}