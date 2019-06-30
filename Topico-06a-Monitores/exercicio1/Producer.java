import java.lang.Math;

public class Producer extends Thread {

    CircularCounter circularCounter;

    Producer(CircularCounter circularCounter) {
        this.circularCounter = circularCounter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long)(Math.random() * 2000));
                int value = (int)(Math.random()*100);
                System.out.println("Inserting " + value);
                this.circularCounter.insert(value);
            } 
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}