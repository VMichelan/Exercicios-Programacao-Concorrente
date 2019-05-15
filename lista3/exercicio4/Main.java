import java.util.ArrayList;

public class Main {

    void w(int ValueToLookFor, ArrayList<Integer> IntArray, int NumberOfThreads) {
        Thread t;
        ArrayList<Thread> ThreadArray = new ArrayList<Thread>();
        for (int i = 0; i < NumberOfThreads; i++) {
            t = new ArraySearchThread(IntArray, ValueToLookFor, i * (IntArray.size() / NumberOfThreads),
                    (i + 1) * (IntArray.size() / NumberOfThreads) - 1);
            ThreadArray.add(t);
            t.setName(Integer.toString(i));
            t.start();

        }
        int count = 0;
        ArraySearchThread ast;
        for (int i = 0; i < ThreadArray.size(); i++) {
            ast = ThreadArray.get(i);
            
        }
        System.out.println("Number of occurances: " + count);
    }

    public static void main(String[] args) {
        int ThreadAmount = Integer.parseInt(args[0]);
        int ValueToLookFor = Integer.parseInt(args[1]);
        ArrayList<Integer> IntArray = new ArrayList<Integer>();
        for (int i = 0; i < 40; i++) {
            IntArray.add((int) (Math.random() * 10));

        }
        System.out.println(IntArray);
        new Main().w(ValueToLookFor, IntArray, ThreadAmount);
    }

}