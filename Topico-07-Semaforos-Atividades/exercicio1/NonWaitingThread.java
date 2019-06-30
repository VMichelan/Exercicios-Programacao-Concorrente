import java.util.concurrent.Semaphore;

public class NonWaitingThread extends Thread {

    Semaphore semaphore;

    NonWaitingThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("Thread 1 release");
            this.semaphore.release();
        } catch (Exception e) {}
    }
}