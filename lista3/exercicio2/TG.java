public class TG extends Thread {
    @Override
    public void run() {
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("tg");
        for (int i = 0; i < 10; i++) {
            new Thread(threadGroup, () -> {
                try {
                    while(true){
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    System.out.println("Interrupted");
                }
            }).start();
            
        }

        threadGroup.interrupt();
    }

}