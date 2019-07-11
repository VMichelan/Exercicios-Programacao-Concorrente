import java.util.concurrent.CountDownLatch;

public class StencilThread extends Thread {
    int index;
    StencilCalculator stencilCalculator;
    CountDownLatch latch;

    StencilThread(int index, StencilCalculator stencilCalculator, CountDownLatch latch) {
        this.index = index;
        this.stencilCalculator = stencilCalculator;
        this.latch = latch;
    }

    @Override
    public void run() {
        this.stencilCalculator.calculate(this.index);
        this.latch.countDown();
    }
}