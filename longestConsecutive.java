class Solution {
//https://leetcode.com/problems/longest-consecutive-sequence/submissions/
    public class Interval {
        int val;
        int size;
        Interval parent;
        
        public Interval(int val) {
            this.val = val;
            this.size=1;
            this.parent=this;
        }
        
        @Override
        public boolean equals(Object o) {
            Interval it = (Interval)o;
            return this.val == it.val;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.parent)^(this.size)^(this.val);
        }
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Interval> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            map.put(nums[i], new Interval(nums[i]));
        }

        for(Integer val : map.keySet()) {
            if(map.containsKey(val-1)) {
                Union(map.get(val), map.get(val-1));
            }
            if(map.containsKey(val+1)) {
                Union(map.get(val), map.get(val+1));
            }
        }
        
        int maxCon = 0;
        for(Integer val : map.keySet()) {
            if(map.get(val).size>maxCon)
                maxCon = map.get(val).size;
        }
        return maxCon;
    }

    public void Union(Interval it1, Interval it2) {
        Interval p1 = find(it1);
        Interval p2 = find(it2);
        if(p1==p2) {
            return;
        }
        if(p1.size<p2.size){
            p1.parent = p2;
        }
        else p2.parent =p1;

        int newSize = p1.size+p2.size;
        p1.size = newSize;
        p2.size = newSize;
    }

    public Interval find(Interval it) {
        if(it.parent==it)
            return it;
        Interval p = it.parent;
        while(p.parent!=p) {
            p = p.parent;
        }
        it.parent = p;
        return p;
    }
}

