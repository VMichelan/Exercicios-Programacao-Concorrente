import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphoreA = new Semaphore(0);
        Semaphore semaphoreB = new Semaphore(0);

        new ThreadA(semaphoreA, semaphoreB).start();
        new ThreadB(semaphoreA, semaphoreB).start();
        
    }
}