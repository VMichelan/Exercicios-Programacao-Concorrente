public class Main {
    public static void main(String args[]) {
        int ThreadAmount = (Integer.parseInt(args[0]));
        for (int i = 0; i < ThreadAmount; i++) {
            new HelloThread().start();
            new Thread(new HelloRunnable()).start();
        }
        System.out.println("Main");
    }
}
