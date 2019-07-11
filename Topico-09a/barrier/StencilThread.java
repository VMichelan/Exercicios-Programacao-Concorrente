import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class StencilThread extends Thread {
    int index;
    StencilCalculator stencilCalculator;
    CyclicBarrier cyclicBarrier;

    StencilThread(int index, StencilCalculator stencilCalculator, CyclicBarrier cyclicBarrier) {
        this.index = index;
        this.stencilCalculator = stencilCalculator;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        this.stencilCalculator.calculate(this.index);
        this.cyclicBarrier.await();
    }
}