import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SpecialBinaryString {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println(performRecursion(input));
    }

    static String performRecursion(String input) {

        ArrayList<String> chunks  = getChunks(input.length(), input);
        ArrayList<String> processed = new ArrayList<>();
        for(int i=0;i<chunks.size();i++) {
            String str = chunks.get(i);
            if(containsComplex(str)) {
                int height = getMinHeight(str);
                String x = str.substring(height, str.length()-height);
                String pre =  str.substring(0, height);
                String suf = str.substring(str.length()-height, str.length());
                String proc = performRecursion(x);
                StringBuilder sb = new StringBuilder();
                sb.append(pre).append(proc).append(suf);

                processed.add(sb.toString());
            }
            else {
                processed.add(chunks.get(i));
            }
        }

        StringBuilder output = new StringBuilder();
        ArrayList<String> res = reverseSortChunks(processed);

        for(String rep : res) {
            output.append(rep);
        }

        return output.toString();
    }

    static int getMinHeight (String str) {
        int curHeight=0;
        int prevHeight;
        int curSlop=1;
        int prevSlop;
        int minHeight=Integer.MAX_VALUE;

        for(int i=0;i<str.length();i++) {
            prevHeight = curHeight;
            curHeight+=str.charAt(i)=='1' ? 1:-1;
            prevSlop=curSlop;
            curSlop=curHeight-prevHeight;
            if(prevSlop<0 && curSlop>0) {
                minHeight = Math.min(minHeight, prevHeight);
            }
        }
        return minHeight;
    }

    static ArrayList<String> reverseSortChunks(ArrayList<String> input) {
        Collections.sort(input, Collections.reverseOrder());
        return input;
    }

    static ArrayList<String> getChunks(int n, String input) {
        ArrayList<String> chunks = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        int sum=0;
        for(int i=0;i<n;i++) {
            sum+=input.charAt(i)=='1' ? 1:-1;
            sb.append(input.charAt(i));
            if(sum==0) {
                chunks.add(sb.toString());
                sb = new StringBuilder("");
            }
        }

        return chunks;
    }

    static boolean containsComplex(String input) {
        int curHeight=0;
        int prevHeight;
        int curSlop=1;
        int prevSlop;

        for(int i=0;i<input.length();i++) {
            prevHeight = curHeight;
            curHeight+=input.charAt(i)=='1' ? 1:-1;
            prevSlop=curSlop;
            curSlop=curHeight-prevHeight;
            if(prevSlop<0 && curSlop>0) {
                if(prevHeight>0) {
                    return true;
                }
            }
        }

        return false;
    }
}
