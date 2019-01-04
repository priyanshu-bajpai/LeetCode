public class RangeModule {

        static int maxSize = 1000000000;
        static boolean [] tree = new boolean[maxSize];
        static boolean [] lazy = new boolean[maxSize];
        static boolean [] isLazy = new boolean[maxSize];

        static int end=99999999;
        static int start=0;
        static int rootNode=0;

        public RangeModule() {

        }

        public void addRange(int left, int right) {
            updateRange(tree, rootNode, start, end, left, right, true);
        }

        public boolean queryRange(int left, int right) {
            return rangeQuery(tree, rootNode, start, end, left, right);
        }

        public void removeRange(int left, int right) {
            updateRange(tree, rootNode, start, end, left, right, false);
        }

        static boolean rangeQuery(boolean [] tree, int node, int start, int end, int l, int r) {
            if(start>end || start>r || end<l) {
                return true;
            }

            int left=node*2+1;
            int right=node*2+2;

            if(isLazy[node]) {
                tree[node]=lazy[node];
                if(start!=end) {
                    isLazy[left]=true;
                    lazy[left]=lazy[node];
                    isLazy[right]=true;
                    lazy[right]=lazy[node];
                }
                isLazy[node]=false;
            }

            if(start>=l && end<=r) {
                return tree[node];
            }

            int mid = (start+end)/2;
            return rangeQuery(tree, left, start, mid, l, r) &&
                    rangeQuery(tree, right, mid+1, end, l, r);
        }

        static void updateRange(boolean [] tree, int node, int start, int end, int l, int r, boolean val) {
            if(start>end || start>r || end<l) {
                return;
            }

            int left=node*2+1;
            int right=node*2+2;

            if(isLazy[node]) {
                tree[node]=lazy[node];
                if(start!=end) {
                    isLazy[left]=true;
                    lazy[left]=lazy[node];
                    isLazy[right]=true;
                    lazy[right]=lazy[node];
                }
                isLazy[node]=false;
            }

            if(start>=l && end<=r) {
                tree[node]=val;
                if(start!=end) {
                    isLazy[left]=true;
                    lazy[left]=val;
                    isLazy[right]=true;
                    lazy[right]=val;
                }
                return;
            }

            int mid = (start+end)/2;
            updateRange(tree, left, start, mid, l, r, val);
            updateRange(tree, right, mid+1, end, l, r, val);
            tree[node] = tree[left]&&tree[right];
            return;
        }
    }

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */