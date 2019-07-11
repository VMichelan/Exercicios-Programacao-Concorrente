import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = Integer.parseInt(args[0]);
        int rowLen = Integer.parseInt(args[1]);
        int colLen = Integer.parseInt(args[2]);

        int[][] matrix = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < rowLen; j++) {
                matrix[i][j] = (int)(Math.random() * 100);
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();

        for (int i = 0; i < rowLen; i++) {
            futureList.add(executorService.submit(new SumLineTask(matrix[i])));
        }

        int finalSum = 0;

        try {
            for (Future<Integer> f : futureList) {
                finalSum += f.get();
            }
        } catch (Exception e) {}

        System.out.println(finalSum);
        
        int realSum = 0;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < rowLen; j++) {
                realSum += matrix[i][j];
            }
        }

        System.out.println(realSum);
        executorService.shutdown();

    }
}