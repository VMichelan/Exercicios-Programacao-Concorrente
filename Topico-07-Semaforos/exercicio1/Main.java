public class Main {

    public static void main(String[] args) {
        CircularCounter circularCounter = new CircularCounter(6);
        Semaphore consumerSemaphore = new Semaphore(0);
        Semaphore producerSemaphore = new Semaphore(6);
        Semaphore binarySemaphore = new Semaphore(1);
        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
            new Consumer(circularCounter,consumerSemaphore,producerSemaphore,binarySemaphore).start();
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            new Producer(circularCounter,consumerSemaphore,producerSemaphore,binarySemaphore).start();
        }
    }

}