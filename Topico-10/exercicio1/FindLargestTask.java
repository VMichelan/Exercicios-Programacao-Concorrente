import java.util.List;
import java.util.concurrent.Callable;

public class FindLargestTask implements Callable<Integer> {

    List<Integer> list;
    int begin;
    int end;

    FindLargestTask(List<Integer> list, int begin, int end) {
        this.list = list;
        this.begin = begin;
        this.end = end;
    }

    public Integer call() {
        int largest = 0;
        for(int i = this.begin; i < this.end; i++) {
            if (this.list.get(i) > largest) {
                largest = this.list.get(i);
            }
        }
        return largest;
    }
    
}