import java.util.concurrent.Semaphore;

public class Smoker extends Thread {
    Semaphore smokerSemaphore;
    Semaphore agentSemaphore;
    String message;

    Smoker(Semaphore smokerSemaphore, Semaphore agentSemaphore, String message) {
        this.smokerSemaphore = smokerSemaphore;
        this.agentSemaphore = agentSemaphore;
        this.message = message;
    }

    public void makeCigarette() {
        try {
            Thread.sleep((long)(Math.random() * 1000));
        } catch (Exception e) {}
    }

    public void smoke() {
        try {
            Thread.sleep((long)(Math.random() * 1000));
        } catch (Exception e) {}
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.smokerSemaphore.acquire();
                System.out.println(message);
                this.makeCigarette();
                this.agentSemaphore.release();
                this.smoke();

            }
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
