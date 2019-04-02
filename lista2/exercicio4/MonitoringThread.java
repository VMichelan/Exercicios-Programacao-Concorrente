import java.util.ArrayList;

/**
 * Cria várias threads que executam infinitamente e outra thread para monitorar o primeiro conjunto de threads mostrando
 * quais estão interrompidas
 */

public class MonitoringThread extends Thread {

    ArrayList<Thread> ThreadArray;

    MonitoringThread(ArrayList<Thread> ThreadArray) {
        this.ThreadArray = ThreadArray;
    }

    @Override
    public void run() {

        while (!this.ThreadArray.isEmpty()) {
            for (int i = 0; i < this.ThreadArray.size(); i++) {
                if (this.ThreadArray.get(i).isInterrupted()) {
                    System.out.println("Thread " + this.ThreadArray.get(i).getName() + " interrupted");

                }
            }

            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }

        }
    }

    public static void main(String[] args) {
        ArrayList<Thread> ThreadArray = new ArrayList<Thread>();
        Thread t;
        for (int i = 0; i < 10; i++) {
            t = new Thread(() -> {
                    while (true) {
                    }
            });
            t.setName(Integer.toString(i));
            t.start();
            ThreadArray.add(t);
        }
        new MonitoringThread(ThreadArray).start();
        try {
            Thread.sleep(1000);

        } catch (Exception e) {
        }
        ThreadArray.get(3).interrupt();
        ThreadArray.get(4).interrupt();
        ThreadArray.get(5).interrupt();
        ThreadArray.get(9).interrupt();
    }
}