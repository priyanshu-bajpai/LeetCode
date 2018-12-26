import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.lang.Math.max;

class Solution {

    public static void main(String[] args) {
        int[][] pos = new int[][]{{6,1},{9,2},{2,4}};
        System.out.println(fallingSquares(pos));
    }

    public static class Node {
        int start;
        int end;
        int val;
        Node right;
        Node left;
        public Node (int st, int end, int val) {
            this.start= st;
            this.end= end;
            this.val= val;
            this.right = null;
            this.left=null;
        }
    }

    public static List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> map = getCompressedIdx(positions);
        int numsquares = positions.length;
        Integer[] maxLength = new Integer[numsquares];

        Node root = createTree(0, map.size()-1, 0);
        for(int i=0;i<numsquares;i++) {
            int[] pos = positions[i];
            int st = pos[0];
            int end = pos[1]+pos[0]-1;
            int i1 = map.get(st);
            int i2 = map.get(end);
            int v1 = getMax(root,  i1, i2);
            update(root, pos[1] + v1, i1, i2);
            maxLength[i] = root.val;
        }

        return Arrays.asList(maxLength);
    }

    static Node createTree(int start, int end, int val) {
        if(start>end)
            return null;
        if(start==end) {
            return new Node(start, end, val);
        }

        Node root = new Node(start, end, val);
        int mid = (start+end)/2;
        root.left = createTree(start, mid, val);
        root.right = createTree(mid+1, end, val);
        return root;
    }

    static int getMax(Node root, int start, int end) {
        if(root.start==root.end) {
            return root.val;
        }
        int mid = (root.start+root.end)/2;
        if(mid>=end) {
            return getMax(root.left, start, end);
        }
        else if(mid<start) {
            return getMax(root.right, start, end);
        }
        else {
            int v1 = getMax(root.left, start, mid);
            int v2 = getMax(root.right,mid+1, end);
            return max(v1, v2);
        }
    }

    static void update(Node root, int val, int start, int end) {
        if(root.start==root.end) {
            root.val=val;
            return;
        }
        int mid = (root.start+root.end)/2;
        if(mid>=end) {
            update(root.left, val, start, end);
        }
        else if(mid<start) {
            update(root.right, val, start, end);
        }
        else {
            update(root.left, val, start, mid);
            update(root.right, val, mid+1, end);
        }

        root.val = max(root.right.val, root.left.val);
    }

    public static HashMap<Integer, Integer> getCompressedIdx(int[][] positions) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<positions.length;i++) {
            int[] pos = positions[i];
            int st = pos[0];
            int end = pos[1]+pos[0]-1;
            set.add(st);
            set.add(end);
        }

        Integer [] compressed  = set.toArray(new Integer[set.size()]);
        Arrays.sort(compressed);

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<compressed.length;i++) {
            map.put(compressed[i], i);
        }

        return map;
    }
}