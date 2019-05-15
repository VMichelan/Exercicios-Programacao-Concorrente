public class NumberGenerator {

    int currentNumber;
    int end;

    NumberGenerator(int start,int end) {
        this.currentNumber = start;
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    public synchronized int nextNumber() {
        return this.currentNumber++;
    }

}