/**
 * Cria threads dos exercicios anteriores e interrompe elas
 */
public class InterruptingMain {
    public static void main(String[] args) {
        Thread sleepyThread = new SleepyThread();
        sleepyThread.start();
        sleepyThread.interrupt();
        Thread ReadingThread = new ReadingThread();
        ReadingThread.start();
        ReadingThread.interrupt();
    }
}