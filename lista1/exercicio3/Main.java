public class Main {
    public static void main(String args[]) {
        int ThreadAmount = (Integer.parseInt(args[0]));
        Counter counter = new Counter();
        for (int i = 0; i < ThreadAmount; i++) {
            new HelloThread(counter).start();
        }
        System.out.println("Main " + counter.getCounter());
    }
}
