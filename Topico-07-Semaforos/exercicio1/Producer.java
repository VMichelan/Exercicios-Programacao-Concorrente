import java.lang.Math;

public class Producer extends Thread {

    CircularCounter circularCounter;
    Semaphore consumerSemaphore;
    Semaphore producerSemaphore;
    Semaphore binarySemaphore;

    Producer(   CircularCounter circularCounter,
                Semaphore consumerSemaphore,
                Semaphore producerSemaphore,
                Semaphore binarySemaphore ) {
        this.circularCounter = circularCounter;
        this.consumerSemaphore = consumerSemaphore;
        this.producerSemaphore = producerSemaphore;
        this.binarySemaphore = binarySemaphore;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.producerSemaphore.P();
                this.binarySemaphore.P();
                Thread.sleep((long)(Math.random() * 1000));
                int value = (int)(Math.random()*100);
                System.out.println("Inserting " + value);
                this.circularCounter.insert(value);
                this.binarySemaphore.V();
                this.consumerSemaphore.V();
            } 
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}
