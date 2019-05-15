import java.util.ArrayList;

public class PrimeCalculatorThread extends Thread {

    NumberGenerator numberGenerator;
    ArrayList<Integer> primeArray;

    PrimeCalculatorThread(NumberGenerator numberGenerator, ArrayList<Integer> primeArray) {
        this.numberGenerator = numberGenerator;
        this.primeArray = primeArray;
    }

    public void addToArray(int number) {
        synchronized(this.primeArray) {
            this.primeArray.add(number);
        }
    }

    @Override
    public void run() {
        int number;
        int end = this.numberGenerator.getEnd();
        boolean isPrime;
        while((number = this.numberGenerator.nextNumber()) <= end) {
            isPrime = true;
            for (int i = 2; i < number; i++) {
                if (number%i == 0) {
                    isPrime = false;
                    break;
                }
                
            }
            if (isPrime){
                this.addToArray(number);
            }
        }
        
    }

    public static void main(String[] args) {
        NumberGenerator numberGenerator = new NumberGenerator(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        int threadAmount = Integer.parseInt(args[2]);
        ArrayList<Integer> primeArray = new ArrayList<Integer>();

        Thread t;
        ArrayList<Thread> threadArray = new ArrayList<Thread>();

        for (int i = 0; i < threadAmount; i++) {
            t = new PrimeCalculatorThread(numberGenerator,primeArray);
            t.start();
            threadArray.add(t);
        }
        for (Thread var : threadArray) {
            try {
                var.join();
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        System.out.println(primeArray);
        System.out.println(primeArray.size());
    }
}