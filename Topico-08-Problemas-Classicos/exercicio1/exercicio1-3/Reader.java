public class Reader extends Thread {
    SharedObject sharedObject;
    ReaderWriter readerWriter;

    Reader(SharedObject sharedObject, ReaderWriter readerWriter) {
        this.sharedObject = sharedObject;
        this.readerWriter = readerWriter;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.readerWriter.startRead();
                System.out.println(this.sharedObject.getSharedInt());
                this.readerWriter.endRead();
                Thread.sleep((int)(Math.random()*500)); 
            } catch (Exception e) {
            }
        }
    }
}