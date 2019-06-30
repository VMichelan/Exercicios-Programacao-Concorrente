public class Semaphore {
    int value;

    Semaphore(int value) {
        this.value = value;
    }

    public synchronized void P() {
        if (this.value <= 0) {
            try {
                this.wait();
            } catch (Exception e) {}
        }

        this.value = this.value - 1;

    }

    public synchronized void V() {
        this.value = this.value + 1;
        this.notify();

    }
}