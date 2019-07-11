import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        int n = 4;

        double[] a = new double[n];
        double[] b = new double[n];

        StencilCalculator stencilCalculator = new StencilCalculator(a, b);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(n-2);

        a[0] = 1;
        a[n-1] = 1;

        b[0] = 1;
        b[n-1] = 1;

        for (int i = 1; i < n-1; i++) {
            a[i] = (double)(Math.random()* 10);
        }

        a[1] = 2;
        a[2] = 2;

        for (int i = 1; i < n-1; i++) {
            new StencilThread(i, stencilCalculator, cyclicBarrier).start();
        }

        do {
            try {
                while (cyclicBarrier.getNumberWaiting() < n-2) {
                    Thread.yield();
                }
            } catch (Exception e) {}
            for (double var : stencilCalculator.p) {
                System.out.print(var + " ");
                
            }
            System.out.println();
            stencilCalculator.swap();
        } while (stencilCalculator.error > 0.01);


    }
    
}