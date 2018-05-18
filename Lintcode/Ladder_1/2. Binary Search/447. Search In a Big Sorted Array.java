public class Solution {
    /*
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return: An integer which is the first index of target.
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {

        //find boundry
        int left = 0;
        int right = 1;
        while (reader.get(right) < target) {
            right *= 2;
        }

        int mid = 0;
        int pos = -1;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            int val = reader.get(mid);
            if (val < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if(reader.get(left) == target) {
            return left;
        }

        if(reader.get(right) == target) {
            return right;
        }

        return -1;
    }
}