import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class ResourceTracker {
    HashMap<Semaphore, Boolean> resourceMap = new HashMap<Semaphore, Boolean>();

    ResourceTracker(ArrayList<Semaphore> resourceSemaphoreList) {
        for (Semaphore s : resourceSemaphoreList) {
            this.resourceMap.putIfAbsent(s, Boolean.valueOf(false));
        }
    }

    public boolean hasResource(Semaphore resource) {
        return (this.resourceMap.get(resource) == true);
    }

    public void getResource(Semaphore resource) {
        this.resourceMap.put(resource, Boolean.valueOf(false));
    }

    public void setResource(Semaphore resource) {
        this.resourceMap.put(resource, Boolean.valueOf(true));
    }
}