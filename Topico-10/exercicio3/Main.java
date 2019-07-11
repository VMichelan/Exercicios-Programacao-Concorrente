import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        int[][] inputMatrixA = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] inputMatrixB = {{9,8,7},{6,5,4},{3,2,1}};
        int[][] resultMatrix = new int[3][3];

        ExecutorService executor = Executors.newFixedThreadPool(4);

        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                futureList.add(executor.submit(new MultiplyMatrixTask(inputMatrixA, 3, 3,inputMatrixB, 3, 3, i, j)));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    resultMatrix[i][j] = futureList.get(i+j).get().intValue();
                    
                } catch (Exception e) {
                    //TODO: handle exception
                }

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
                System.out.println();
        }

        executor.shutdown();
    }
    
}