public class HelloRunnable implements Runnable {

    @Override
    public void run(){
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
        }
        System.out.println("Runnable");
    }

    public static void main(String args[]){
        System.out.println("Olá Mundo");
    }
}
