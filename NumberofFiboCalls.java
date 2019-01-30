import javafx.util.Pair;

public class NumberofFiboCalls {
//https://arena.topcoder.com/#/u/practiceCode/10829/2128/2292/2/265041
//    public static void main(String args[]) {
//        NumberofFiboCalls nofc = new NumberofFiboCalls();
//        int[] res = nofc.fiboCallsMade(22);
//        System.out.println(res[0] + " " + res[1]);
//    }
    int[] fiboCallsMade(int n) {
        Pair<Integer, Integer> dp[] = new Pair[3];
        dp[0] = new Pair<>(1,0);
        dp[1] = new Pair<>(0,1);
        if(n==0||n==1) {
            return new int[] {dp[n].getKey(), dp[n].getValue()};
        }

        int idx=2;
        for(int num=2;num<=n;num++) {
            int prevIdx = (idx+2)%3;
            int secPrevIdx = (idx+1)%3;
            dp[idx]= new Pair<>(dp[secPrevIdx].getKey() + dp[prevIdx].getKey(),
                    dp[secPrevIdx].getValue() + dp[prevIdx].getValue());

            idx=(idx+1)%3;
        }
        idx = (idx+2)%3;
        return new int[]{dp[idx].getKey(), dp[idx].getValue()};
    }
}
