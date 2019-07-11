import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Intermediator {

    ArrayList<Semaphore> resourcesSemaphoreList;
    ArrayList<Smoker> smokerList;
    Semaphore mutex;
    ResourceTracker resourceTracker;
    ArrayList<IntermediatorThread> intermediatorList;

    Intermediator(ArrayList<Semaphore> resourcesSemaphoreList, ArrayList<Smoker> smokerList) {

        this.resourcesSemaphoreList = resourcesSemaphoreList;
        this.smokerList = smokerList;

        this.mutex = new Semaphore(1);
        this.resourceTracker = new ResourceTracker(resourcesSemaphoreList);

        this.intermediatorList = new ArrayList<IntermediatorThread>();

        for (Semaphore s : resourcesSemaphoreList) {
            intermediatorList.add(new IntermediatorThread(s, this.smokerList, this.mutex, this.resourceTracker));
            
        }

    }

    public void start() {
        for (IntermediatorThread t : this.intermediatorList) {
            t.start();
        }
    }

}

class IntermediatorThread extends Thread {
    Semaphore resourceToLookFor;
    ArrayList<Smoker> smokerList;
    Semaphore mutex;
    ResourceTracker resourceTracker;

    IntermediatorThread(Semaphore resourceToLookFor, ArrayList<Smoker> smokerList, Semaphore mutex, ResourceTracker resourceTracker) {
        this.resourceToLookFor = resourceToLookFor;
        this.smokerList = smokerList;
        this.mutex = mutex;
        this.resourceTracker = resourceTracker;
    }

    public void run() {
        try {
            while (true) {
                this.resourceToLookFor.acquire();
                this.mutex.acquire();
                this.resourceTracker.setResource(this.resourceToLookFor);
                for (Smoker s : this.smokerList) {
                    if (s.canMakeCigarette(this.resourceTracker.resourceMap)) {
                        for (Semaphore ns : s.neededResourcesList) {
                            this.resourceTracker.getResource(ns);
                        }

                        s.addTicket();
                        break;
                    }
                }
                this.mutex.release();
            }
        } catch (Exception e) {
        }

    }

}