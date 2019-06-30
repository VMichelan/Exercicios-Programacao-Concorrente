import java.util.concurrent.Semaphore;

public class DuoSemaphore {
    Semaphore s1;
    Semaphore s2;

    DuoSemaphore() {
        this.s1 = new Semaphore(0, true);
        this.s2 = new Semaphore(0, true);
    }

    public void acquireFirstQueue() {
        try {
            synchronized (this.s1) {
                this.s2.release();
                this.s1.acquire();
            }
        } catch (Exception e) {}
    }

    public void acquireSecondQueue() {
        try {
            synchronized (this.s2) {
            this.s1.release();
            this.s2.acquire();
            }
        } catch (Exception e) {}
    }

}