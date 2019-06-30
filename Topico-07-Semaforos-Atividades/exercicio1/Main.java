import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);
        
        new WaitThread(semaphore).start();
        new NonWaitingThread(semaphore).start();
    }
}
