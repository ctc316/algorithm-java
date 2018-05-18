/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<Interval>();
        }

        List<Interval> results = new ArrayList<Interval>();
        int[] idx = new int[intervals.size()];

        while (true) {
            // pick a interval
            int min = Integer.MAX_VALUE;
            Interval curr = new Interval(0, 0);
            int currListIdx = -1;
            boolean found = false;

            for (int i = 0; i < intervals.size(); i++) {
                List<Interval> list = intervals.get(i);
                if (idx[i] == list.size()) {
                    continue;
                }

                Interval candidate = list.get(idx[i]);
                if (candidate.start < min) {
                    min = candidate.start;
                    curr = candidate;
                    currListIdx = i;
                    found = true;
                }
            }

            if (!found) {
                break;
            }

            idx[currListIdx]++;


            // splice as many as possible
            boolean keepGoing = true;
            while (keepGoing) {
                keepGoing = false;
                for (int i = 0; i < intervals.size(); i++) {
                    List<Interval> list = intervals.get(i);
                    if (idx[i] == list.size()) {
                        continue;
                    }

                    Interval candidate = list.get(idx[i]);
                    if (candidate.start <= curr.end) {
                        if (curr.end < candidate.end) {
                            curr.end = candidate.end;
                        }
                        idx[i]++;
                        keepGoing = true;
                    }
                }
            }

            results.add(curr);
        }

        return results;
    }
}