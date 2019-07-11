import java.util.concurrent.Callable;

public class MultiplyMatrixTask implements Callable<Integer> {
    int[][] ma;
    int ar;
    int ac;
    int br;
    int bc;
    int[][] mb;
    int i;
    int j;
    
    MultiplyMatrixTask(int[][] ma, int ar, int ac, int[][] mb, int br, int bc, int i, int j) {
        this.ma = ma;
        this.mb = mb;
        this.i = i;
        this.j = j;
        this.ar = ar;
        this.ac = ac;
        this.br = br;
        this.bc = bc;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int h = 0; h < this.ar; h++) {
            result += ma[this.i][h] * mb[h][this.j]; 
            
        }
        return result;
    }
}