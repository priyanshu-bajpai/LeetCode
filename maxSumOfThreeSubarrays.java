class Solution {
//https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/submissions/
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n =nums.length;
        int [][] dp = new int [3][n+1];
        int[] sumEndingHere = new int[n+1];
        int tail=k;

        for(int i=1; i<=n;i++) {
            sumEndingHere[i] = sumEndingHere[i-1] + nums[i-1];
            sumEndingHere[i]-= (i-1-k>=0) ? nums[i-1-k] : 0;

            dp[0][i] = (i>=k) ? Math.max(sumEndingHere[i], dp[0][i-1]) : 0;
        }

        for(int i=1;i<3;i++) {
            for(int j=k;j<=n;j++) {
              dp[i][j] = Math.max(dp[i-1][j-k] + sumEndingHere[j] , dp[i][j-1]);
            }
        }

        int[] res = new int[3];
        int row=2;
        int col=n;
        int idx=2;
        while(row>=0 && col>=0) {
            if(dp[row][col]==dp[row][col-1]) {
                col--;
            }
            else if(col-k>=0 && dp[row][col] == (row-1>=0 ? dp[row-1][col-k]:0) + sumEndingHere[col]) {
                res[idx] = col-k;
                idx--;
                row--;
                col-=k;
                if(idx<0)
                    break;
            }
        }
        return res;
    }
}