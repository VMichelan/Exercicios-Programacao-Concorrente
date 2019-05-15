import java.util.ArrayList;

public class MergeSortThread extends Thread {
    int[] IntArray;
    int l;
    int r;
    int m;

    MergeSortThread(int[] IntArray, int l, int r) {
        this.IntArray = IntArray;
        this.l = l;
        this.r = r;
    }

    @Override
    public void run() {
        if ((this.r - this.l) > 0) {
            int m = (this.l + this.r) / 2;
            Thread t1 = new MergeSortThread(this.IntArray, this.l, m);
            Thread t2 = new MergeSortThread(this.IntArray, m + 1, this.r);
            try {
                t1.start();
                t2.start();
                t1.join();
                t2.join();
            } catch (Exception e) {
                // TODO: handle exception
            }
            merge(this.IntArray, this.l, this.r, m);
        }

    }

    public static void merge(int[] array, int l, int r, int m) {
        int i, j, k;
        int s1 = m - l + 1;
        int s2 = r - m;
        int[] v1 = new int[s1];
        int[] v2 = new int[s2];
        for (i = 0; i < s1; i++) {
            v1[i] = array[l + i];
        }
        for (i = 0; i < s2; i++) {
            v2[i] = array[m + i + 1];
        }

        i = 0;

        j = 0;
        k = l;
        while (i < s1 && j < s2) {
            if (v1[i] <= v2[j]) {
                array[k] = v1[i];
                i++;
            } else {
                array[k] = v2[j];
                j++;
            }
            k++;
        }

        while (i < s1) {
            array[k] = v1[i];
            i++;
            k++;
        }

        while (j < s2) {
            array[k] = v2[j];
            j++;
            k++;
        }

    }

    public static void main(String[] args) {
        int size = 10_0;
        int[] IntArray = new int[size];
        for (int i = 0; i < size; i++) {
            IntArray[i] = (int) (Math.random() * size);

        }
        for (int var : IntArray) {
            System.out.print(var + " ");
            
        }
        System.out.println();
        try {
            Thread t = new MergeSortThread(IntArray, 0, size-1);
            t.start();
            t.join();
        } catch (Exception e) {
        }
        for (int var : IntArray) {
            System.out.print(var + " ");
            
        }
    }

}