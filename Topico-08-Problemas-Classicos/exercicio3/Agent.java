import java.util.Random;
import java.util.concurrent.Semaphore;

public class Agent extends Thread {
    private Semaphore agentSemaphore;
    private Semaphore tobaccoSemaphore;
    private Semaphore paperSemaphore;
    private Semaphore matchesSemaphore;

    Agent() {
        this.agentSemaphore = new Semaphore(1);
        this.tobaccoSemaphore = new Semaphore(0);
        this.paperSemaphore = new Semaphore(0);
        this.matchesSemaphore = new Semaphore(0);
    }

    public Semaphore getAgentSemaphore() {
        return agentSemaphore;
    }

    public Semaphore getTobaccoSemaphore() {
        return tobaccoSemaphore;
    }

    public Semaphore getPaperSemaphore() {
        return paperSemaphore;
    }

    public Semaphore getMatchesSemaphore() {
        return matchesSemaphore;
    }

    @Override
    public void run() {
        Random randomNumberGenerator = new Random();
        try {
            while (true) {

                this.agentSemaphore.acquire();

                switch (randomNumberGenerator.nextInt(3)) {
                    case 0:
                        this.tobaccoSemaphore.release();
                        this.paperSemaphore.release();
                        System.out.println("Releasing tobacco and paper");
                        break;
                    case 1:
                        this.paperSemaphore.release();
                        this.matchesSemaphore.release();
                        System.out.println("Releasing matches and paper");
                        break;
                    case 2:
                        this.tobaccoSemaphore.release();
                        this.matchesSemaphore.release();
                        System.out.println("Releasing tobacco and matches");
                }
            }
        } catch (Exception e) {}
    }

}