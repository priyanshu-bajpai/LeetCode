import java.util.Arrays;
import java.util.Scanner;

public class MonkSecretServices {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i=0;i<t;i++) {
            int buildings = sc.nextInt();
            int roads = sc.nextInt();
            long[][] mat = createMat(buildings, roads, sc);

            int S = sc.nextInt()-1;
            int A = sc.nextInt()-1;
            int H = sc.nextInt()-1;

            System.out.println(minTimeToWait(mat, S, A, H));
        }
    }

    public static long[][] createMat(int numB, int numR, Scanner sc) {
        long [][] mat = new long[numB][numB];
        for(long[] row : mat) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for(int i=0;i<numR;i++) {
            int b1 = sc.nextInt()-1;
            int b2 = sc.nextInt()-1;
            mat[b1][b2] = Math.min(sc.nextInt(), mat[b1][b2]);
            mat[b2][b1] = mat[b1][b2];
        }
        return mat;
    }

    public static long minTimeToWait(long[][] mat, int S, int A, int H) {
        int n = mat.length;

        for(int k=0;k<n;k++) {
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }

        long maxTime=0;
        for(int i=0;i<n;i++) {
            if(i==S||i==H||i==A) {
                continue;
            }

            maxTime = Math.max(mat[S][i] + mat[i][A] + mat[A][i] + mat[i][H], maxTime);
        }

        return maxTime;
    }
}
