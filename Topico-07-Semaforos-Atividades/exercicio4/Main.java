import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Thread {

    Semaphore semaphore;

    Main(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.semaphore.acquire();
                System.out.println(Math.random()*100);
                this.semaphore.release();
                Thread.sleep(100);
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        for (int i = 0; i < 30; i++) {
            new Main(semaphore).start();
            
        }
        
    }
}