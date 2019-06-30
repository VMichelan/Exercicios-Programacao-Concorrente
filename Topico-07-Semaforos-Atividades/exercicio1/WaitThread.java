import java.util.concurrent.Semaphore;

public class WaitThread extends Thread {
    Semaphore semaphore;

    WaitThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread 2 waiting for Thread 1");
            this.semaphore.acquire();
            System.out.println("Thread 2 ready");
        } catch (Exception e) {}
    }
}