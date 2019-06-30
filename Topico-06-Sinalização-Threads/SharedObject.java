public class SharedObject {
    int sharedInt;
    boolean bufferFull = false;

    public synchronized boolean isBufferFull() {
        return this.bufferFull;
    }

    public synchronized void setBufferFull(boolean value){
        this.bufferFull = value;
    }
    /**
     * @param sharedInt the sharedInt to set
     */
    public synchronized void setSharedInt(int sharedInt) {
        this.sharedInt = sharedInt;
    }
    /**
     * @return the sharedInt
     */
    public synchronized int getSharedInt() {
        return sharedInt;
    }
}