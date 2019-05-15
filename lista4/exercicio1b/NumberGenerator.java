import java.util.concurrent.atomic.AtomicInteger;

public class NumberGenerator {

    AtomicInteger currentNumber = new AtomicInteger();
    int end;

    NumberGenerator(int start,int end) {
        this.currentNumber.set(start);
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    public int nextNumber() {
        return this.currentNumber.getAndIncrement();
    }

}