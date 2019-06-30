import java.util.concurrent.Semaphore;

public class DummyQueueTwo implements Runnable {

    DuoSemaphore duoSemaphore;

    DummyQueueTwo(DuoSemaphore duoSemaphore) {
        this.duoSemaphore = duoSemaphore;
    }

    @Override
    public void run() {

        try {
            while (true) {
                System.out.println("Waiting " + Thread.currentThread().getName() + " from second queue");
                this.duoSemaphore.acquireSecondQueue();
                System.out.println("Running " + Thread.currentThread().getName() + " from second queue");
                Thread.sleep((long)(Math.random() * 1_000));
                System.out.println("Finished " + Thread.currentThread().getName() + " from second queue");
            }
        } catch (Exception e) {}

    }

}