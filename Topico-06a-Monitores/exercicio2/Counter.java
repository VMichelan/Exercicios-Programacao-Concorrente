public class Counter {
    int count = 0;
    int sleepUntil = 0;

    public synchronized void increment() {
        this.count += 1;
        if (this.count == this.sleepUntil) {
            this.notify();
        }
    }

    public synchronized void sleepUntil(int x) {
        this.sleepUntil = x;
        while (this.count < this.sleepUntil) {
            try {
                this.wait();
            } catch (Exception e) {}
        }
    }
}