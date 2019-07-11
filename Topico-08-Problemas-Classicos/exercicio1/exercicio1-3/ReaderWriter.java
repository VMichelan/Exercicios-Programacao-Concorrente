import java.util.concurrent.Semaphore;

public class ReaderWriter {
    int buffer;
    int numReaders = 0;
    int writerWaiting = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore wlock = new Semaphore(1);
    Object readerWait;
    Object writerWait;

    public void startRead() throws InterruptedException {
        this.mutex.acquire();
        if (this.writerWaiting > 0) {
            this.mutex.release();
            this.writerWait.notify();
            do {
                this.readerWait.wait();
            } while (this.writerWaiting > 0);
            this.mutex.acquire();
        }
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
        this.mutex.acquire();
        if (!this.wlock.tryAcquire()) {
            this.writerWaiting++;
            this.mutex.release();
            do {
                this.writerWait.wait();
            } while (!this.wlock.tryAcquire());
            this.mutex.acquire();
        }
    }

    public void endWrite() throws InterruptedException {
        this.writerWaiting--;
        this.mutex.release();
        this.wlock.release();
        if (this.writerWaiting > 0) {
            this.writerWait.notify();
        }
        else {
            this.readerWait.notifyAll();
        }
    }
}
