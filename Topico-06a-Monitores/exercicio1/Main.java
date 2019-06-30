public class Main {

    public static void main(String[] args) {
        CircularCounter circularCounter = new CircularCounter(6);
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            new Producer(circularCounter).start();
        }
        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
            new Consumer(circularCounter).start();
            
        }
    }

}