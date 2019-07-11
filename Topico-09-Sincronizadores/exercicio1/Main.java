public class Main {
    public static void main(String[] args) {

        Counter c = new Counter();

        for (int i = 0; i < 10; i++) {
            new IncrementThread(c).start();
        }
        for (int i = 0; i < 10; i++) {
            new DecrementThread(c).start();
        }
        
    }
}

class IncrementThread extends Thread {
    Counter counter;

    IncrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            
            while (true) {
                System.out.println(this.counter.incrementAndGet());
                Thread.sleep(100);
            }
        } catch (Exception e) {
        }
    }
}

class DecrementThread extends Thread {
    Counter counter;

    DecrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            
            while (true) {
                System.out.println(this.counter.decrementAndGet());
                Thread.sleep(100);
            }
        } catch (Exception e) {
        }
    }
}