public class Main {
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        ReaderWriter readerWriter = new ReaderWriter();

        for (int i = 0; i < 5; i++) {
            new Reader(sharedObject, readerWriter).start();
        }
        
        for (int i = 0; i < 5; i++) {
            new Writer(sharedObject, readerWriter).start();
        }
    }
}
