import java.io.FileInputStream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

class CountLetters implements Runnable {
    ConcurrentHashMap<Character, Integer> map;
    int begin;
    int end;
    String file;
    static int a;

    CountLetters(ConcurrentHashMap<Character, Integer> map, int begin, int end, String file) {
        this.map = map;
        this.begin = begin;
        this.end = end;
        this.file = file;
    }

    @Override
    public void run() {
        int currentvalue;
        for (int i = begin; i < end; i++) {
            if (this.map.putIfAbsent(this.file.charAt(i), 1) == null) {
                continue;
            }
            do {
                currentvalue = this.map.get(this.file.charAt(i));
            } while (!this.map.replace(this.file.charAt(i), currentvalue, currentvalue + 1));
        }
        System.out.println("FINISHED");
    }
}

public class Main {
    public static void main(String[] args) {
        ConcurrentHashMap<Character, Integer> map = new ConcurrentHashMap<Character, Integer>();
        File file = new File("lorem-ipsum");
        String str;
        try {

            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            str = new String(data, "UTF-8");
            ExecutorService executor = Executors.newFixedThreadPool(4);

            int i;
            int ratio = str.length()/4;
            for (i = 0; i < 3; i++) {
                executor.submit(new CountLetters(map, i*ratio, (i+1)*ratio, str));
            }

            executor.submit(new CountLetters(map, i*ratio, str.length(), str));

            try {
                executor.shutdown();
                executor.awaitTermination(3, TimeUnit.SECONDS);
            } catch (Exception e) {
                //TODO: handle exception
            }

            System.out.println(map);
        } catch (Exception e) {
            //TODO: handle exception
        }


    }
}
