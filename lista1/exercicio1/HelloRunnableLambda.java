public class HelloRunnableLambda {

    public static void main(String args[]){
        new Thread( () -> {
            System.out.println("Thread using Lambda, thread id:" + Thread.currentThread().getId());
        }).start();
    }
}
