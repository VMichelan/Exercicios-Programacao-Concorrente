import java.util.concurrent.Semaphore;

public class DummyQueueOne implements Runnable {

    DuoSemaphore duoSemaphore;

    DummyQueueOne(DuoSemaphore duoSemaphore) {
        this.duoSemaphore = duoSemaphore;
    }

    @Override
    public void run() {

        try {
            while (true) {
                System.out.println("Waiting " + Thread.currentThread().getName() + " from first queue");
                this.duoSemaphore.acquireFirstQueue();
                System.out.println("Running " + Thread.currentThread().getName() + " from first queue");
                Thread.sleep((long)(Math.random() * 1_000));
                System.out.println("Finished " + Thread.currentThread().getName() + " from first queue");
            }
        } catch (Exception e) {}

    }

}