// Version 1: Quick Sort, Time: O(nlogn)
public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        quickSort(A, 0, A.length - 1);
    }

    private void quickSort(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = A[start + (end - start) / 2];
        int left = start, right = end;

        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }
            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }

        quickSort(A, start, right);
        quickSort(A, left, end);
    }
}


// Version 2: Merge Sort, Time: O(nlogn), Space: O(n)
public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        mergeSort(A, 0, A.length - 1, new int[A.length]);
    }

    private void mergeSort(int[] A, int start, int end, int temp[]) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;

        mergeSort(A, start, mid, temp);
        mergeSort(A, mid + 1, end, temp);

        // merge
        int left = start, right = mid + 1;
        int idx = start;
        while (left <= mid && right <= end) {
            if (A[left] < A[right]) {
                temp[idx++] = A[left++];
            } else {
                temp[idx++] = A[right++];
            }
        }

        while (left <= mid) {
            temp[idx++] = A[left++];
        }
        while (right <= end) {
            temp[idx++] = A[right++];
        }

        for (int i = start; i <= end; i++) {
            A[i] = temp[i];
        }
    }
}


// Version 3: Heap Sort, Time: O(nlogn), Space: O(n)
public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(A.length);
        for (int i = 0; i < A.length; i++) {
            heap.offer(A[i]);
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = heap.poll();
        }
    }
}