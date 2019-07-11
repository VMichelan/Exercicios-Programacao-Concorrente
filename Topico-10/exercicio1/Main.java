import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        int numberOfThreads = Integer.parseInt(args[0]);
        int arraySize = Integer.parseInt(args[1]);
        int numberOfTasks = Integer.parseInt(args[2]);

        ArrayList<Integer> list = new ArrayList<Integer>(arraySize);
        for (int i = 0; i < arraySize; i++) {
            list.add((int)(Math.random()*100));
        }

        ArrayList<FindLargestTask> taskList = new ArrayList<FindLargestTask>(numberOfTasks);
        for (int i = 0; i < numberOfTasks; i++) {
            taskList.add(new FindLargestTask(list, i*(arraySize/numberOfTasks), (i+1)*(arraySize/numberOfTasks)));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        try {
            List<Future<Integer>> futureList = executorService.invokeAll(taskList);
            ArrayList<Integer> resultList = new ArrayList<Integer>(numberOfTasks);
            for (Future<Integer> f : futureList) {
                resultList.add(f.get());
            }

            Future<Integer> result = executorService.submit(new FindLargestTask(resultList, 0, resultList.size()));
            System.out.println(result.get());
            
            result = executorService.submit(new FindLargestTask(list, 0, list.size()));
            System.out.println(result.get());
        } catch (Exception e) {}

        System.out.println(list);

        executorService.shutdown();

    }


}
