public class Consumer extends Thread {

    CircularCounter circularCounter;

    Consumer(CircularCounter circularCounter) {
        this.circularCounter = circularCounter;
    }

    @Override
    public void run() {
        int value;
        try {
            while (true) {
                value = this.circularCounter.getValue();
                System.out.println(value);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}