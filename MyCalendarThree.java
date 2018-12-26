//https://leetcode.com/problems/my-calendar-iii/submissions/
class MyCalendarThree {

    static TreeMap<Integer, Integer> intervals;
    public MyCalendarThree() {
        intervals = new TreeMap<>();
    }

    public int book(int start, int end) {
        addTreeMap(start, end);
        return getCountLiveEvents();
        
    }

    static void addTreeMap(int start, int end) {
        intervals.put(start, intervals.getOrDefault(start, 0) + 1);
        intervals.put(end, intervals.getOrDefault(end, 0) -1);
    }
    
    static int getCountLiveEvents() {
        int maxLiveEvents=0;
        int curLiveEvents=0;
        for(int val:intervals.values()) {
            curLiveEvents+=val;
            maxLiveEvents = Math.max(curLiveEvents, maxLiveEvents);
        }
        return maxLiveEvents;
    }
}










