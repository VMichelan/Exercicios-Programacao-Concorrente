import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ReusableBarrier {

    Semaphore semaphore;
    int barrierSize;
    AtomicInteger queueSize;

    ReusableBarrier(int barrierSize) {
        this.barrierSize = barrierSize;
        this.semaphore = new Semaphore(0);
        this.queueSize = new AtomicInteger(0);
    }

    public void acquire() throws InterruptedException {
       if (this.queueSize.incrementAndGet() == this.barrierSize) {
           this.queueSize.set(0);
           this.semaphore.release(this.barrierSize);
       } 
       this.semaphore.acquire();
    }
}