public class Main extends Thread {
    
    BoundedCounter boundedCounter;

    Main(BoundedCounter boundedCounter) {
        this.boundedCounter = boundedCounter;
    }

    @Override
    public void run() {
        if (this.getName().equals("1")) {
            while (true) {
                this.boundedCounter.increment();
            }
        } else {
            while (true) {
                this.boundedCounter.decrement();
            }

        }
    }

    public static void main(String[] args) {
        BoundedCounter boundedCouter = new BoundedCounter(-50, 50);
        Thread t = new Main(boundedCouter);
        t.setName("1");
        t.start();
        t = new Main(boundedCouter);
        t.setName("2");
        t.start();
    }
}