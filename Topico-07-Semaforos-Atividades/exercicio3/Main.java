import java.util.concurrent.Semaphore;

public class Main extends Thread {

    Semaphore semaphore;
    Counter counter;

    Main(Semaphore semaphore, Counter counter) {
        this.semaphore = semaphore;
        this.counter = counter;
    }

    @Override
    public void run() {
        while (true) {
            try {
            this.semaphore.acquire();
            System.out.println(this.counter.getCount());
            this.counter.increment();
            this.semaphore.release();
            Thread.sleep(100);
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        Counter counter = new Counter();

        for (int i = 0; i < 30; i++) {
            new Main(semaphore,counter).start();
            
        }
        
    }
}