public class HelloThread extends Thread {

    Counter counter;

    public HelloThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            int count = this.counter.getCounter();
            count = count + 1;
            this.counter.setCounter(count);
            Thread.sleep(1000);
            System.out.println("Thread " + count);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String args[]) {
        System.out.println("Olá Mundo");
    }
}
