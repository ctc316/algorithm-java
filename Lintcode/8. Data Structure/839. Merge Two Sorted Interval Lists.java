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
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        List<Interval> results = new ArrayList<>();
        int p1 = 0, p2 = 0;

        while (p1 < list1.size() || p2 < list2.size()) {
            // pick an intveral
            Interval curr;
            if ((p2 == list2.size()) ||
                (p1 < list1.size() && list1.get(p1).start < list2.get(p2).start)) {
                curr = list1.get(p1);
                p1++;
            } else {
                curr = list2.get(p2);
                p2++;
            }

            // splice as many as possible
            while (p1 < list1.size() || p2 < list2.size()) {
                if (p1 < list1.size() && splice(curr, list1.get(p1))) {
                    p1++;
                    continue;
                }
                if (p2 < list2.size() && splice(curr, list2.get(p2))) {
                    p2++;
                    continue;
                }
                break;
            }

            results.add(curr);
        }

        return results;
    }

    private boolean splice(Interval base, Interval addi) {
        if (addi.start <= base.end) {
            if (addi.end > base.end) {
                base.end = addi.end;
            }
            return true;
        }
        return false;
    }
}