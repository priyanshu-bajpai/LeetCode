import java.util.Stack;

public class MaximalRectangle {

    static public int maximalRectangle(char[][] matrix) {
        int n=matrix.length;
        int [][] dp = getTransformed(matrix);

        int maxArea=0;
        for(int i=0;i<n;i++) {
            maxArea = Math.max(maxArea, getMaxArea(dp[i]));
        }
        return maxArea;
    }

    public static int getMaxArea(int []mat) {
        int m = mat.length;
        Stack<Integer> st = new Stack<>();
        int maxArea=0;

        int i=0;
        while(i<m) {

            if(st.isEmpty() || mat[i] >= mat[st.peek()]) {
                st.push(i);
            }
            else {
                int idx = st.pop();
                int leftBound = st.isEmpty() ? -1 : st.peek();
                maxArea = Math.max(mat[idx] * (i - leftBound -1), maxArea);
                i--;
            }

            i++;
        }

        while(!st.isEmpty()) {
            int idx = st.pop();
            int leftBound = st.isEmpty()? -1 : st.peek();
            maxArea = Math.max(maxArea, mat[idx]*(m-leftBound-1));
        }

        return maxArea;
    }

    static int[][] getTransformed(char[][] mat) {
        int n=mat.length;
        int m=mat[1].length;
        int [][]dp = new int[n][m];

        for(int j=m-1;j>=0;j--) {
            dp[n-1][j] = (mat[n-1][j]=='1') ? 1:0;
            for(int i=n-2;i>=0;i--) {
                dp[i][j] = (mat[i][j]=='0') ? 0 : 1 + dp[i+1][j];
            }
        }
        return dp;
    }


}







