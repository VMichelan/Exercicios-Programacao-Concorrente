public class Consumer extends Thread {

    CircularCounter circularCounter;
    Semaphore consumerSemaphore;
    Semaphore producerSemaphore;
    Semaphore binarySemaphore;

    Consumer(   CircularCounter circularCounter,
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
        int value;
        try {
            while (true) {
                this.consumerSemaphore.P();
                this.binarySemaphore.P();
                value = this.circularCounter.getValue();
                System.out.println(value);
                this.binarySemaphore.V();
                this.producerSemaphore.V();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}