public class Main extends Thread {

    Counter counter;

    Main(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println("Sleeping");
        this.counter.sleepUntil(3);
        System.out.println("Awaken");
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Main(counter).start();        
        try {
            Thread.sleep(100);
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println("Increment");
        counter.increment();
        System.out.println("Increment");
        counter.increment();
        System.out.println("Increment");
        counter.increment();
    }
}