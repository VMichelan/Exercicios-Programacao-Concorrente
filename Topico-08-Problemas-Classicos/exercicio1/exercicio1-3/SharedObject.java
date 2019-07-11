public class SharedObject {
    int sharedInt = 0;

    public synchronized void setSharedInt(int sharedInt) {
        this.sharedInt = sharedInt;
    }

    public synchronized int getSharedInt() {
        return sharedInt;
    }
}