public class Consumer extends Thread {
    SharedObject sharedObject;
    Object monitoringObject;

    Consumer(SharedObject sharedObject, Object monitorinObject) {

        this.sharedObject = sharedObject;
        this.monitoringObject = monitorinObject;

    }

    private void doNotify() {
        synchronized (this.monitoringObject) {
            this.monitoringObject.notify();
        }
    }

    private void doWait() {
        synchronized(this.monitoringObject) {
            try {
                this.monitoringObject.wait();
            } catch (Exception e) {}
        }
    }

    @Override
    public void run() {
        while (true) {

            while (!this.sharedObject.isBufferFull()) {
                this.doWait();
            }

            System.out.println(this.sharedObject.getSharedInt());

            this.sharedObject.setBufferFull(false);
            this.doNotify();
        }
        
    }
}