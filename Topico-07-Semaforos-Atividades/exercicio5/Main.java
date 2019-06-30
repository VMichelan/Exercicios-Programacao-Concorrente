import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Thread {

    Barrier barrier;

    Main(Barrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long)(Math.random()*5_000));
                this.barrier.acquire();
                System.out.println(Math.random()*100);
                Thread.sleep((long)(Math.random()*1_000 + 500));
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        Barrier barrier = new Barrier(10);

        for (int i = 0; i < 30; i++) {
            new Main(barrier).start();
            
        }
        
    }
}