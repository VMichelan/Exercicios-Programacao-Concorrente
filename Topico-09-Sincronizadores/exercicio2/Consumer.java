public class Consumer extends Thread {
    FIFO f;

    Consumer(FIFO f) {
        this.f = f;
    }

    public void run() {
        while (true) {
            try {
                System.out.println(this.f.pop());
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }


}