public class Producer extends Thread {
    FIFO f;

    Producer(FIFO f) {
        this.f = f;
    }

    public void run() {
        int v;
        while (true) {
            try {
                v = (int)(Math.random() * 1000);
                System.out.println("Inserting " + v);
                this.f.push(v);
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }

}