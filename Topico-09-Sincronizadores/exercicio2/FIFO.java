import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FIFO {

    Lock l = new ReentrantLock();
    Condition notFull = l.newCondition();
    Condition notEmpty = l.newCondition();
    int size = 100;
    List<Integer> fifo = new LinkedList<Integer>(); 

    public void push(int value) {
        this.l.lock();
        try {
            while (this.fifo.size() >= this.size) {
                try {
                    this.notFull.await();
                } catch (Exception e) {}
            }
            this.fifo.add(value);
            this.notEmpty.signal();
        } finally {
            l.unlock();
        }
    }

    public int pop() {
        this.l.lock();
        try {
            while (this.fifo.isEmpty()) {
                try {
                    this.notEmpty.await();
                } catch (Exception e) {}
            }
            int value = this.fifo.get(0);
            this.fifo.remove(0);
            this.notFull.signal();
            return value;

        } finally {
            this.l.unlock();
        }
    }

}