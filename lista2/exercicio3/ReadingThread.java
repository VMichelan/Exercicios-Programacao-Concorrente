import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadingThread extends Thread {

    @Override
    public void run() {

        ArrayList<String> quoteList = new ArrayList<>();

        FileReader file = null;
        BufferedReader br = null;
        try {
            file = new FileReader("quotes.txt");
            br = new BufferedReader(file);
            for (String line; (line = br.readLine()) != null;) {
                quoteList.add(line);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {

            }
        }

        

        while (true) {
            try {
                System.out.println(quoteList.get((int) (Math.random() * quoteList.size())));
                Thread.sleep(10000);
            } catch (Exception e) {
                if (e instanceof InterruptedException) {
                    System.out.println("Interrupted, cleaning stuff...");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        new ReadingThread().start();
    }

}