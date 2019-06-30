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
        return this.count > this.size;
    }

    public synchronized void insert(int value) {
        array[this.end] = value;
        this.end += 1;
        this.end = this.end % this.size;
        this.count += 1;
    }

    public synchronized int getValue() {
       int returnValue = this.array[this.begin];
       this.begin += 1;
       this.begin = this.begin % this.size;
       this.count -= 1;
       return returnValue;
    }

}
