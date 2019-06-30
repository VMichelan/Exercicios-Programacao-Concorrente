public class Producer extends Thread {

    SharedObject sharedObject;
    Object monitoringObject;

    Producer(SharedObject sharedObject, Object monitorinObject) {

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
            while (this.sharedObject.isBufferFull()) {
                this.doWait();
            }
            try {
                Thread.sleep((int)(Math.random() * 500));
            } catch (Exception e) {}
            this.sharedObject.setSharedInt((int)(Math.random() * 100));
            this.sharedObject.setBufferFull(true);
            this.doNotify();
        }

    }
}