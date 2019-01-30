//https://leetcode.com/problems/search-in-rotated-sorted-array-ii/submissions/

class Solution {
    public boolean search(int[] nums, int target) {
        return search(nums, target, 0, nums.length-1) == -1 ? false : true;
    }

    int search(int[] nums, int target, int lo, int hi) {
        if(lo>hi) return -1;

        int mid=(lo+hi)/2;
        if(nums[mid]==target) {
            return mid;
        }

        else if(nums[mid] > nums[hi] && nums[mid] > nums[lo]) {
            if(nums[lo] <= target && nums[mid] >= target) {
                return doBinarySearch(nums, target, lo ,hi);
            }
            else {
                return search(nums, target, mid+1, hi);
            }
        }

        else if(nums[lo] <= nums[mid] && nums[hi] >= nums[mid]) {
            int first = search(nums, target, lo, mid-1);
            int second = search(nums, target, mid+1, hi);
            if(first!=-1 || second!=-1) {
                return first==-1 ? second : first;
            }
        }

        else if(nums[lo] > nums[mid] && nums[mid] < nums[hi]) {
            if(target >= nums[mid] && target<=nums[hi]) {
                return doBinarySearch(nums, target, mid, hi);
            }
            else {
                return search(nums, target, lo, mid-1);
            }
        }

        else {
            int first = search(nums, target, lo, mid-1);
            int second = search(nums, target, mid+1, hi);
            if(first!=-1 || second!=-1) {
                return first==-1 ? second : first;
            }
        }

        return -1;
    }

    int doBinarySearch(int [] nums, int target, int lo, int hi) {
        while(lo<=hi) {
            int mid= (lo+hi)/2;
            if(nums[mid]==target) {
                return mid;
            }
            else if(nums[mid]>target) {
                hi=mid-1;
            }
            else {
                lo=mid+1;
            }
        }

        return -1;
    }
}