import java.util.ArrayList;

public class ThreadStatusWatcher extends Thread {
    @Override
    public void run() {
        
    }

    public static void main(String[] args) {
        ArrayList<Thread> threadArray = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threadArray.add(new Thread(() -> {
                try {
                    Thread.sleep((long)((Math.random()+1) * 2000));
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }));
            threadArray.get(i).setName(Integer.toString(i));
            threadArray.get(i).start();
            
        }

        while(true){
            for (Thread t : threadArray) {
                System.out.println("Thread " + t.getName() + " is " + t.getState());
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }

}