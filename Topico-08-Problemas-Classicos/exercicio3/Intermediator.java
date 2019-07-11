import java.util.concurrent.Semaphore;

public class Intermediator {

    TobaccoIntermediator tobaccoIntermediator;
    PaperIntermediator paperIntermediator;
    MatchesIntermediator matchesIntermediator;
    Semaphore tobaccoSmokerSemaphore;
    Semaphore paperSmokerSemaphore;
    Semaphore matchesSmokerSemaphore;
    Semaphore mutex;
    ResourceTracker resourceTracker;

    Intermediator(Agent agent) {
        this.tobaccoSmokerSemaphore = new Semaphore(0);
        this.paperSmokerSemaphore = new Semaphore(0);
        this.matchesSmokerSemaphore = new Semaphore(0);

        new Smoker(this.tobaccoSmokerSemaphore, agent.getAgentSemaphore(), "tobacco smoker").start();
        new Smoker(this.paperSmokerSemaphore, agent.getAgentSemaphore(), "paper smoker").start();
        new Smoker(this.matchesSmokerSemaphore, agent.getAgentSemaphore(), "matches smoker").start();

        this.mutex = new Semaphore(1);
        this.resourceTracker = new ResourceTracker();
        this.tobaccoIntermediator = new TobaccoIntermediator();
        this.tobaccoIntermediator.construct(agent, mutex, resourceTracker, tobaccoSmokerSemaphore, paperSmokerSemaphore,
                matchesSmokerSemaphore);

        this.paperIntermediator = new PaperIntermediator();
        this.paperIntermediator.construct(agent, mutex, resourceTracker, tobaccoSmokerSemaphore, paperSmokerSemaphore,
                matchesSmokerSemaphore);

        this.matchesIntermediator = new MatchesIntermediator();
        this.matchesIntermediator.construct(agent, mutex, resourceTracker, tobaccoSmokerSemaphore, paperSmokerSemaphore,
                matchesSmokerSemaphore);

    }

    public void start() {
        this.tobaccoIntermediator.start();
        this.paperIntermediator.start();
        this.matchesIntermediator.start();
    }

}

class ResourceTracker {
    boolean hasTobacco = false;
    boolean hasPaper = false;
    boolean hasMatches = false;
}

abstract class AbstractIntermediator extends Thread {
    Semaphore agentSemaphore;
    Semaphore tobaccoSemaphore;
    Semaphore paperSemaphore;
    Semaphore matchesSemaphore;
    Semaphore tobaccoSmokerSemaphore;
    Semaphore paperSmokerSemaphore;
    Semaphore matchesSmokerSemaphore;
    Semaphore mutex;
    ResourceTracker resourceTracker;

    public void construct(Agent agent, Semaphore mutex, ResourceTracker resourceTracker,
            Semaphore tobaccoSmokerSemaphore, Semaphore paperSmokerSemaphore, Semaphore matchesSmokerSemaphore) {
        this.agentSemaphore = agent.getAgentSemaphore();
        this.tobaccoSemaphore = agent.getTobaccoSemaphore();
        this.paperSemaphore = agent.getPaperSemaphore();
        this.matchesSemaphore = agent.getMatchesSemaphore();

        this.tobaccoSmokerSemaphore = tobaccoSmokerSemaphore;
        this.paperSmokerSemaphore = paperSmokerSemaphore;
        this.matchesSmokerSemaphore = matchesSmokerSemaphore;

        this.mutex = mutex;
        this.resourceTracker = resourceTracker;
    }

    public abstract void acquireSemaphore() throws InterruptedException;

    public abstract void handleResource();

    public void run() {
        try {
            while (true) {
                this.acquireSemaphore();
                this.mutex.acquire();
                this.handleResource();
                this.mutex.release();
            }
        } catch (Exception e) {
        }

    }

}

class TobaccoIntermediator extends AbstractIntermediator {

    public void acquireSemaphore() throws InterruptedException {
        this.tobaccoSemaphore.acquire();
    }

    public void handleResource() {
        if (this.resourceTracker.hasPaper) {
            this.resourceTracker.hasPaper = false;
            this.matchesSmokerSemaphore.release();
        } else if (this.resourceTracker.hasMatches) {
            this.resourceTracker.hasMatches = false;
            this.paperSmokerSemaphore.release();
        } else {
            this.resourceTracker.hasTobacco = true;
        }
    }
}

class PaperIntermediator extends AbstractIntermediator {

    public void acquireSemaphore() throws InterruptedException {
        this.paperSemaphore.acquire();
    }

    public void handleResource() {
        if (this.resourceTracker.hasTobacco) {
            this.resourceTracker.hasTobacco = false;
            this.matchesSmokerSemaphore.release();
        } else if (this.resourceTracker.hasMatches) {
            this.resourceTracker.hasMatches = false;
            this.tobaccoSmokerSemaphore.release();
        } else {
            this.resourceTracker.hasPaper = true;
        }
    }
}

class MatchesIntermediator extends AbstractIntermediator {

    public void acquireSemaphore() throws InterruptedException {
        this.matchesSemaphore.acquire();
    }

    public void handleResource() {
        if (this.resourceTracker.hasPaper) {
            this.resourceTracker.hasPaper = false;
            this.tobaccoSmokerSemaphore.release();
        } else if (this.resourceTracker.hasTobacco) {
            this.resourceTracker.hasTobacco = false;
            this.paperSmokerSemaphore.release();
        } else {
            this.resourceTracker.hasMatches = true;
        }

    }
}