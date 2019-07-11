public class Main {

    public static void main(String[] args) {
        FIFO f = new FIFO();

        for (int i = 0; i < 10; i++) {
            new Consumer(f).start();
        }
        for (int i = 0; i < 10; i++) {
            new Producer(f).start();
        }

    }

}