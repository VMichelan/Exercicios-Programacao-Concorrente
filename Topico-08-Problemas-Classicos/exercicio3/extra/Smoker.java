import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.HashMap;

public class Smoker extends Thread {
    Semaphore smokerSemaphore;
    Semaphore agentSemaphore;
    ArrayList<Semaphore> neededResourcesList;
    String a;

    Smoker(ArrayList<Semaphore> neededResourcesList, Semaphore agentSemaphore, String a) {
        this.smokerSemaphore = new Semaphore(0);
        this.agentSemaphore = agentSemaphore;
        this.neededResourcesList = neededResourcesList;
        this.a = a;
    }


    public boolean canMakeCigarette(HashMap<Semaphore, Boolean> resourceMap) {
        int count = 0;
        for (Semaphore s : neededResourcesList) {
            if (resourceMap.get(s) == true) {
                count++;
            } 
        }

        if (count == this.neededResourcesList.size()) {
            return true;
        }

        return false;
    }

    public void addTicket() {
        this.smokerSemaphore.release();
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
                System.out.println(a);
                this.makeCigarette();
                this.agentSemaphore.release();
                this.smoke();

            }
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}