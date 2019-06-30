public class BoundedCounter {
    int counter = 0;
    int min;
    int max;

    BoundedCounter(int min,int max) {
        this.min = min;
        this.max = max;
    }

    public synchronized void increment() {
        try {
            this.counter += 1;
            while (this.counter == this.max) {
                System.out.println("MAX");
                Thread.sleep(150);
                this.wait();
            }
            this.notify();
            System.out.println(this.counter);
            Thread.sleep(20);
        } catch (Exception e) {}
    }

    public synchronized void decrement() {
        try {
            
            this.counter -= 1;
            while (this.counter == this.min) {
                System.out.println("MIN");
                Thread.sleep(150);
                this.wait();
            }
            this.notify();
            System.out.println(this.counter);
            Thread.sleep(20);
        } catch (Exception e) {}
    }

}