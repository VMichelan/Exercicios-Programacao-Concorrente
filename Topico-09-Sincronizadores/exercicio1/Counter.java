import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    Lock lock = new ReentrantLock();
    private int counter = 0;

    public int incrementAndGet() {
        this.lock.lock();
        try {
            return ++this.counter;
        } finally {
            this.lock.unlock();
        }

    }

    public int decrementAndGet() {
        this.lock.lock();
        try {
            return --this.counter;
        } finally {
            this.lock.unlock();
        }

    }
    public int getCounter() {
        this.lock.lock();
        try {
            return this.counter;
        } finally {
            this.lock.unlock();
        }
    }


}