public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }

        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }

    private void rainbowSort(int[] colors, int start, int end, int colorStart, int colorEnd) {
        if (start >= end || colorStart == colorEnd) {
            return;
        }

        // [3, 2, 1, 3, 2, 2, 1, 4, 2, 1, 4]   l:0, r:9   
        // [1, 2, 1, 3, 2, 2, 1, 4, 2, 3, 4]   l:3, r:7
        // [1, 2, 1, 2, 2, 2, 1, 4, 3, 3, 4]   l:7, r:6

        int left = start;
        int right = end;
        int pivot = (colorStart + colorEnd) / 2;
        while (left <= right) {
            while (left <= right && colors[left] <= pivot) {
                left++;
            }
            while (left <= right && colors[right] > pivot) {
                right--;
            }
            if(left <= right) {
                int temp = colors[left];
                colors[left] = colors[right];
                colors[right] = temp;
                left++;
                right--;
            }
        }
        rainbowSort(colors, start, right, colorStart, pivot);
        rainbowSort(colors, left, end, pivot + 1, colorEnd);
    }
}