public class HelloThread extends Thread {

    @Override
    public void run(){
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
        }
        System.out.println("Thread");
    }

    public static void main(String args[]){
        System.out.println("Olá Mundo");
    }
}
