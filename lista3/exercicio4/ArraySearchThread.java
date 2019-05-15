import java.util.ArrayList;

public class ArraySearchThread extends Thread {

    ArrayList<Integer> IntArray;
    int begin;
    int end;
    int ValueToLookFor;
    int count;

    public void asd(){
        
    }

    public int getCount() {
        return this.count;
    }

    ArraySearchThread(ArrayList<Integer> IntArray,int ValueToLookFor,int begin,int end){
        this.IntArray = IntArray;
        this.begin = begin;
        this.end = end;
        this.ValueToLookFor = ValueToLookFor;
        this.count = 0;
    }

    @Override
    public void run() {
        for (int i = this.begin; i <= this.end;i++) {
            if(this.IntArray.get(i) == this.ValueToLookFor){
                this.count += 1;
            }
            
        }
        System.out.println(this.count);
        
    }

}