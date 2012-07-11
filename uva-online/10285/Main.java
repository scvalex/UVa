import java.util.*;
import java.io.*;

public class Main {

    static int mat[][];
    static int h[][];
    static int r, c;
    static final int maxHeight = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n > 0) {
            String line[] = br.readLine().split(" ");
            r = Integer.parseInt(line[1]);
            c = Integer.parseInt(line[2]);

            h = new int[r][c];
            mat = new int[r][c];

            for (int i=0;i<r;i++) {
                String heights[] = br.readLine().split(" ");
                for (int j=0;j<c;j++) {
                    h[i][j] = Integer.parseInt(heights[j]);
                }
            }

            for (int i=0;i<r;i++) {
                for (int j=0;j<c;j++) {
                    if (localMinima(i, j))
                        mat[i][j] = 1;
                }
            }
            
            /*      for (int i=0;i<r;i++)
                System.out.println(Arrays.toString(mat[i]));
            */

            boolean increased = true;
            int k;
            for (k=2;k<=maxHeight && increased;k++) {
                increased = false;
                
                for (int i=0;i<r;i++)
                    for (int j=0;j<c;j++) {
                        if (mat[i][j] == k - 1)
                            increased |= update(i, j, k);
                    }

                /*              System.out.format("---------------- Iteration %d --------------\n", k);
                for (int i=0;i<r;i++)
                    System.out.println(Arrays.toString(mat[i]));
                */
            }

            System.out.format("%s: %d\n", line[0], k - 2);
            n--;
        }
            
        br.close();
    }

    static boolean localMinima(int i, int j) {
        boolean isMin = true;
        isMin &= (i - 1 <  0 || h[i][j] <= h[i-1][j]);
        isMin &= (j - 1 <  0 || h[i][j] <= h[i][j-1]);
        isMin &= (i + 1 >= r || h[i][j] <= h[i+1][j]);
        isMin &= (j + 1 >= c || h[i][j] <= h[i][j+1]);
        return isMin;
    }

    static boolean update(int i, int j, int k) {
        boolean updated = false;
        if ( i - 1 >= 0 && h[i][j] < h[i-1][j]) {
            mat[i-1][j] = k;
            updated = true;
        }
        if ( i + 1 <  r && h[i][j] < h[i+1][j]) {
            mat[i+1][j] = k;
            updated = true;
        }
        if ( j - 1 >= 0 && h[i][j] < h[i][j-1]) {
            mat[i][j-1] = k;
            updated = true;
        }
        if ( j + 1 <  c && h[i][j] < h[i][j+1]) {
            mat[i][j+1] = k;
            updated = true;
        }
        return updated;
    }
}
