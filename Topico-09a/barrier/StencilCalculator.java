public class StencilCalculator {

    double[] a;
    double[] b;
    double[] p;
    double error;

    StencilCalculator(double[] a, double[] b) {
        this.a = a;
        this.p = a;
        this.b = b;
        this.error = Float.MAX_VALUE; 
    }

    public void calculate(int index) {
        b[index] = (a[index-1] + a[index+1])/2;
        double currentError = Math.abs(a[index] - b[index]);

        synchronized(this) {
            if (currentError < this.error) {
                this.error = currentError;
            }
        }
    }

    public void swap() {
        this.p = this.b;
        this.b = this.a;
        this.a = this.p;
    }
}