public class Writer extends Thread {
    SharedObject sharedObject;
    ReaderWriter readerWriter;

    Writer(SharedObject sharedObject, ReaderWriter readerWriter) {
        this.sharedObject = sharedObject;
        this.readerWriter = readerWriter;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.readerWriter.startWrite();
                this.sharedObject.setSharedInt((int)(Math.random()*1000));
                this.readerWriter.endWrite();
                Thread.sleep((int)(Math.random()*500)); 
            } catch (Exception e) {
            }
        }
    }
}