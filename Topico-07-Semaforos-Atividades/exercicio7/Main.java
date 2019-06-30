public class Main {

    public static void main(String[] args) {
        DuoSemaphore duoSemaphore = new DuoSemaphore();
        Thread t;

        for (int i = 0; i < 4; i++) {
            t = new Thread(new DummyQueueOne(duoSemaphore));
            t.start();
        }

        for (int i = 0; i < 4; i++) {
            t = new Thread(new DummyQueueTwo(duoSemaphore));
            t.start();
        }
    }
}