import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CountSmall {

    public static void main(String args[]) {
        int [] nums = new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};

        System.out.println(countSmaller(nums));
    }

    static int []lazy;
    static public List<Integer> countSmaller(int[] nums) {
        if(nums.length==0)
            return new ArrayList<>();
        HashMap<Integer, Integer> compressed = getCompressedMap(nums);
        int n = compressed.size();
        System.out.println(n);
        int[] tree = new int[10000];
        lazy = new int[10000];

        Integer[] res = new Integer[nums.length];
        for (int i=nums.length-1;i>=0;i--) {
            int st = compressed.get(nums[i]);
            int end = n-1;

            res[i]  = rangeQuery(tree, 0, 0, n-1, st, st);
            updateRange(tree, 0, 0, n-1, 0, end, 1);
        }

        return Arrays.asList(res);
    }

    static void updateRange(int[] tree, int node, int start, int end, int l, int r, int val) {
        if(start>end || start>r || end<l) {
            return;
        }

        int left = node*2 +1;
        int right = node*2 +2;
//        System.out.println("Node:" +node + "Left: " + left + " right: " + right);
        if(lazy[node]!=0) {
            tree[node]+=(end-start+1) * lazy[node];
            if(start!=end) {
                lazy[left] = lazy[node];
                lazy[right] = lazy[node];
            }
            else {
                System.out.println("Left: " + left + " right: " + right);
            }
            lazy[node]=0;
        }

        if(start>=l && end<=r) {
            tree[node] +=(end-start+1)*val;
            if(start!=end) {
                lazy[left]+=val;
                lazy[right]+=val;
            }

            return;
        }

        int mid = (start+end)/2;
        updateRange(tree, left, start, mid, l, r, val);
        updateRange(tree, right, mid+1, end, l, r, val);
        tree[node]=tree[left]+tree[right];
        return;
    }

    static int rangeQuery(int[] tree, int node, int start, int end, int l, int r) {
        if (start > end || start > r || end < l) {
            return 0;
        }

        int left = node * 2 + 1;
        int right = node * 2 + 2;
        System.out.println("Node:" +node + "Left: " + left + " right: " + right);
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if(start!=end) {
                lazy[left] = lazy[node];
                lazy[right] = lazy[node];
            }
            lazy[node] = 0;
        }

        if (start >= l && end <= r) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return rangeQuery(tree, left, start, mid, l, r) +
                rangeQuery(tree, right, mid + 1, end, l, r);
    }

    static HashMap<Integer, Integer> getCompressedMap(int [] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++) {
            set.add(nums[i]);
        }
        Integer[] arr = set.toArray(new Integer[set.size()]);
        Arrays.sort(arr);

        for(int i=0;i<arr.length;i++) {
            map.put(arr[i], i);
        }

        return map;
    }
}
