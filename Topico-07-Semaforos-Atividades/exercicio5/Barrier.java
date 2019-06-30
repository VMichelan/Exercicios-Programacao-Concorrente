import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Barrier {

    Semaphore semaphore;
    int barrierSize;
    boolean isOpen;
    AtomicInteger queueSize;

    Barrier(int barrierSize) {
        this.semaphore = new Semaphore(0);
        this.barrierSize = barrierSize;
        this.isOpen = false;
        this.queueSize = new AtomicInteger(0);
    }

    public void acquire() throws InterruptedException {
        if (this.isOpen) {
            return;
        } 

        int currentQueueSize = this.queueSize.incrementAndGet();
        if (this.barrierSize == currentQueueSize) {
            this.isOpen = true;
            this.semaphore.release(1);
        }

        this.semaphore.acquire();
        this.semaphore.release();

    }
}
