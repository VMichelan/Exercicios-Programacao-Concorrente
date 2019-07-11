import java.util.concurrent.Semaphore;

public class ReaderWriter {
    int buffer;
    int numReaders = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore wlock = new Semaphore(1);

    public void startRead() throws InterruptedException {
        this.mutex.acquire();
        if (this.numReaders == 0) {
            this.mutex.release();
            this.wlock.acquire();
            this.mutex.acquire();
        }
        this.numReaders++;
        this.mutex.release();

    }

    public void endRead() throws InterruptedException {
        this.mutex.acquire();
        this.numReaders--;
        if (this.numReaders == 0)
            this.wlock.release();
        this.mutex.release();
    }

    public void startWrite() throws InterruptedException {
        this.wlock.acquire();
        this.mutex.acquire();
    }

    public void endWrite() throws InterruptedException {
        this.wlock.release();
        this.mutex.release();
    }
}