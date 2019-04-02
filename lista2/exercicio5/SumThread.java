import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Cria uma thread para ler números de stdin e guardar em um array, aguarda a thread terminar usando join() e realiza a soma
 */

public class SumThread extends Thread {

    ArrayList<Integer> NumList;

    SumThread(ArrayList<Integer> NumList) {
        this.NumList = NumList;
    }

    @Override
    public void run() {
        int number = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while ((number = Integer.parseInt(br.readLine())) != 0) {
                this.NumList.add(number);
            }
            
        }
        catch(Exception e){

        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> NumList = new ArrayList<Integer>();
        Thread t = new SumThread(NumList);
        t.start();
        try {
            t.join();
        } catch (Exception e) {
        }
        int sum = 0;
        for (int i = 0; i < NumList.size(); i++) {
            sum += NumList.get(i);

        }
        System.out.println(sum);
    }

}