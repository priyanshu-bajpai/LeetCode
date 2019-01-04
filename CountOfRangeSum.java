public class CountOfRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        if(n==0)
            return 0;
        int[] tree = new int[4*n];

        return buildTree(tree, 0, 0, n-1, nums, lower, upper);
    }


    static int buildTree(int[] tree, int node, int start, int end,
                         int [] nums, int lower, int upper) {
        if(start==end) {
            tree[node] = nums[start];
            return (tree[node]>=lower && tree[node]<=upper) ? 1:0;
        }

        int left = node*2+1;
        int right = node*2+2;

        int mid = (start+end)/2;
        int lr = buildTree(tree, left, start, mid, nums, lower, upper);
        int rr = buildTree(tree, right, mid+1, end, nums, lower, upper);

        tree[node] = tree[left] + tree[right];

        return lr + rr + ((tree[node] >= lower && tree[node]<=upper) ? 1:0);
    }
}
