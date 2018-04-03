// Solution 1: Two Pointer, Time: O(n1 + n2)
public class Solution {

    /*
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int p1 = 0, p2 = 0;
        ArrayList<Integer> results = new ArrayList<>();
        while (p1 < nums1.length && p2 < nums2.length) {
            while (p1 < nums1.length - 1 && nums1[p1] == nums1[p1 + 1]) {
                p1++;
            }
            while (p2 < nums2.length - 1 && nums2[p2] == nums2[p2 + 1]) {
                p2++;
            }
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                results.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        int[] ret = new int[results.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = results.get(i);
        }
        return ret;
    }
};


// Solution 1: HashSet, Time: O(n1 + n2)
public class Solution {

    /*
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> results = new ArrayList<>();

        for(int n : nums1){
            set.add(n);
        }

        for(int n : nums2){
            if(set.contains(n)){
                results.add(n);
                set.remove(n);
            }
        }

        int[] ret = new int[results.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = results.get(i);
        }
        return ret;
    }
};