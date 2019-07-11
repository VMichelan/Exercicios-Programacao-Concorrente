import java.util.concurrent.Callable;

public class SumLineTask implements Callable<Integer> {
    int[] row;

    SumLineTask(int[] row) {
        this.row = row;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < this.row.length; i++) {
            sum += row[i];
        }

        return sum;
    }
}