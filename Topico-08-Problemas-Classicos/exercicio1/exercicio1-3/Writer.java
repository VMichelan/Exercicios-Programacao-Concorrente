public class Writer extends Thread {
    SharedObject sharedObject;
    ReaderWriter readerWriter;

    Writer(SharedObject sharedObject, ReaderWriter readerWriter) {
        this.sharedObject = sharedObject;
        this.readerWriter = readerWriter;
    }

    @Override
    public void run() {
        int v;
        while (true) {
            try {
                v = (int)(Math.random()*1000);
                this.readerWriter.startWrite();
                this.sharedObject.setSharedInt(v);
                this.readerWriter.endWrite();
                System.out.println(v);
                Thread.sleep((int)(Math.random()*500)); 
            } catch (Exception e) {
            }
        }
    }
}