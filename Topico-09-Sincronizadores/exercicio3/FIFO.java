import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ArrayListThreadSafe {

    Lock l = new ReadWriteLock();
    Lock r = l.getR

    int size = 100;
    List<Integer> fifo = new LinkedList<Integer>(); 

    public void push(int value) {
    }

    public int pop() {

}