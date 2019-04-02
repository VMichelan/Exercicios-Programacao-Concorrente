/** 
* Cria três threads que esperam um tempo aleatório e terminam.
*/
public class SleepyThread extends Thread {

    @Override
    public void run() {
        try {
            long randomTime = (long) (1000 + (Math.random() * 1000 + 1));
            System.out.println("Waiting " + randomTime + " ms");
            Thread.sleep(randomTime);
        } catch (Exception e) {
            if (e instanceof InterruptedException) {
                System.out.println("Interrupted, cleaning stuff...");
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new SleepyThread().start();

        }
    }
}