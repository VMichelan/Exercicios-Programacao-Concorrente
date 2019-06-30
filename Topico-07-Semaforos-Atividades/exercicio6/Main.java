import java.util.Random;

public class Main extends Thread {

    ReusableBarrier barrier;

    Main(ReusableBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.barrier.acquire();
                System.out.println(Math.random()*100);
                Thread.sleep((long)(Math.random()*1000));
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        ReusableBarrier barrier = new ReusableBarrier(10);

        for (int i = 0; i < 30; i++) {
            new Main(barrier).start();
            
        }
        
    }
}