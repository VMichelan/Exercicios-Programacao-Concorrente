public class CircularCounter extends Thread {
    int[] array;
    int begin;
    int end;
    int size;
    int count;

    CircularCounter(int size) {
        this.begin = 0;
        this.end = 0;
        this.size = size;
        this.count = 0;
        this.array = new int[size];
    }

    private synchronized boolean isEmpty() {
        return this.count == 0;
    }

    private synchronized boolean isFull() {
        return this.count == this.size;
    }

    public synchronized void insert(int value) {
        while (this.isFull()) {
            try {
                this.wait();
            } catch (Exception e) {}
        }
        array[this.end] = value;
        this.end += 1;
        this.end = this.end % this.size;
        this.count += 1;
        this.notifyAll();
    }

    public synchronized int getValue() {
       while (this.isEmpty()) {
           try {
               this.wait();
           } catch (Exception e) {}
       } 
       int returnValue = this.array[this.begin];
       this.begin += 1;
       this.begin = this.begin % this.size;
       this.count -= 1;
       this.notifyAll();
       return returnValue;
    }

}