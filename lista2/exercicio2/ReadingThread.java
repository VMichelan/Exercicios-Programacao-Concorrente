import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Cria uma thread que lê citações de um arquivo chamado quotes.txt e mostra uma citação aleatória a cada 10 segundos
 */
public class ReadingThread extends Thread {

    @Override
    public void run() {

        ArrayList<String> quoteList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("quotes.txt"))) {
            for (String line; (line = br.readLine()) != null;) {
                quoteList.add(line);
            }
        } catch (Exception e) {

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