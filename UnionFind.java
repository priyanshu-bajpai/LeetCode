import java.util.Scanner;

public class UnionFind {

    static class Coord {
        int prow;
        int pcol;
        public Coord(int row, int col) {
            this.prow = row;
            this.pcol = col;
        }
    }

    static int n =3;
    static int[][] d= new int[][]{{1,-1,0,0},{0,0,1,-1}};
    static Coord [][] p = new Coord[n][n];

/*    static {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                p[i][j] = new Coord(i, j);
            }
        }
    }*/

    static int numIsland = 0;

    public static void main(String args[]) {
        int [][] mat = new int[][] {{0,0,0},{0,0,0},{0,0,0}};

        Scanner sc = new Scanner(System.in);
        int i, j;
        while((i=sc.nextInt())!=-1) {
            j = sc.nextInt();
            System.out.println(getNumIslands(mat, i, j));
        }
    }

    static int getNumIslands(int mat[][], int row, int col) {

        insertIsland(mat, row, col);

        for (int i=0;i<4;i++) {
            if(checkValid(row + d[0][i], col + d[1][i]) &&
                     mat[row + d[0][i]][col + d[1][i]] == 1) {

                Coord p1 = find(row, col);
                Coord p2 = find(row + d[0][i], col + d[1][i]);

                if (p1 != p2) {
                    union(row, col, row + d[0][i], col + d[1][i]);
                }
            }
        }
        printMatrix(mat);
        return numIsland;
    }

    static void insertIsland(int[][] mat, int row, int col) {
        mat[row][col] = 1;
        p[row][col] = new Coord(row, col);
        numIsland++;
    }

    static boolean checkValid(int row, int col) {
        return row>=0 && row<n && col>=0 && col< n;
    }

    static void union(int row1, int col1, int row2, int col2) {
        Coord root1 = find(row1, col1);
        int row1p = root1.prow;
        int col1p = root1.pcol;

        Coord root2 = find(row2, col2);

        p[row1p][col1p] = root2;

        numIsland--;
    }

    static Coord find(int row, int col) {
        int prow = p[row][col].prow;
        int pcol = p[row][col].pcol;

        if ( !(row == prow && col == pcol)) {
            Coord parent = find(prow, pcol);
            p[row][col] = parent;
        }

        return p[row][col];
    }

    static void printMatrix(int [][] mat) {
        for (int i = 0; i< n; i++) {
            for (int j = 0; j< n; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
