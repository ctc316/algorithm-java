// Version 1: Divide & Conquer: Time: O(nlogk), Space: O(n)
public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }

        return merge(arrays, 0, arrays.length - 1);
    }

    private int[] merge(int[][] arrays, int start, int end) {
        if (start == end) {
            return arrays[start];
        }

        int mid = start + (end - start) / 2;
        int[] left = merge(arrays, start, mid);
        int[] right = merge(arrays, mid + 1, end);
        int[] result = new int[left.length + right.length];

        int l = 0, r = 0, idx = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                result[idx++] = left[l++];
            } else {
                result[idx++] = right[r++];
            }
        }

        while (l < left.length) {
            result[idx++] = left[l++];
        }

        while (r < right.length) {
            result[idx++] = right[r++];
        }

        return result;
    }
}


// Version 2: Merge (Non-Recurisive): Time: O(nlogk), Space: O(n)
public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }

        int end = arrays.length - 1;
        while (end > 0) {
            int count = 0, i = 0;
            while (i <= end - 1) {
                arrays[count++] = merge(arrays[i], arrays[i + 1]);
                i += 2;
            }
            if (i == end) {
                arrays[count++] = arrays[i];
            }
            end = count - 1;
        }
        return arrays[0];
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int l = 0, r = 0, idx = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                result[idx++] = left[l++];
            } else {
                result[idx++] = right[r++];
            }
        }

        while (l < left.length) {
            result[idx++] = left[l++];
        }

        while (r < right.length) {
            result[idx++] = right[r++];
        }

        return result;
    }
}


// Version 3: Heap (Priority Queue); Time: O(nlogn), Space: O(n)
public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                pq.add(arrays[i][j]);
            }
        }

        int[] result = new int[pq.size()];
        int count = 0;
        while (pq.size() > 0) {
            result[count++] = pq.poll();
        }

        return result;
    }
}