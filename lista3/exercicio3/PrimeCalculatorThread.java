public class PrimeCalculatorThread extends Thread {
    int begin, end = 0;

    PrimeCalculatorThread(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " computing primes from " + this.begin + " to " + this.end);
        if (this.begin%2 == 0){
            this.begin += 1;
        }
        if (this.end%2 == 0){
            this.end -= 1;
        }
        if (this.begin == 1) {
            System.out.println(2);
        }
        boolean isPrime;
        int j;
        for (int i = this.begin; i < this.end; i += 2) {
            isPrime = true;
            for (j = i-1; j > 1; j--) {
                if (i%(float)j == 0){
                    isPrime = false;
                    break;
                }
            }
            if (isPrime){
                System.out.println(i);
            }
            
        }
    }

    public static void main(String[] args) {
        int ThreadAmount = Runtime.getRuntime().availableProcessors();
        System.out.println("Running " + ThreadAmount + " Threads.");
        Thread t;
        for (int i = 0; i < ThreadAmount; i++) {
            t = new PrimeCalculatorThread(i*(10000/ThreadAmount) + 1, (i+1)*(10000/ThreadAmount));
            t.setName(Integer.toString(i));
            t.start();
            
        }
    }
}
