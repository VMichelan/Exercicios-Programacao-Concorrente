public class Main {

    public static void main(String[] args) {
        Object monitoringObject = new Object();

        SharedObject sharedObject = new SharedObject();
        new Consumer(sharedObject, monitoringObject).start();
        new Producer(sharedObject, monitoringObject).start();
    }
}