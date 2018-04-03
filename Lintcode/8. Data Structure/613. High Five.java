/**
 * Definition for a Record
 * class Record {
 *     public int id, score;
 *     public Record(int id, int score){
 *         this.id = id;
 *         this.score = score;
 *     }
 * }
 */

public class Solution {

    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
    public Map<Integer, Double> highFive(Record[] results) {
       Map<Integer, Double> avgs = new HashMap<>();
       if(results == null || results.length == 0) {
           return avgs;
       }

       // put scores into min heap by individual
       Map<Integer, PriorityQueue<Integer>> minHeapMap = new HashMap<>();
       for (Record r : results) {
           PriorityQueue<Integer> minHeap;
           if (minHeapMap.containsKey(r.id)) {
                minHeap = minHeapMap.get(r.id);
           } else {
                minHeap = new PriorityQueue<>(5);
                minHeapMap.put(r.id, minHeap);
           }

           if (minHeap.size() < 5) {
               minHeap.offer(r.score);
           } else {
               // compare 5th
               if (r.score > minHeap.peek()) {
                   minHeap.poll();
                   minHeap.offer(r.score);
               }
           }
       }

       // calculate top 5 avgs
       for (Integer key : minHeapMap.keySet()) {
            PriorityQueue<Integer> minHeap = minHeapMap.get(key);
            double size = Math.min(5, minHeap.size());
            int sum = 0;
            for (int i = 0; i < size; i++) {
                sum += minHeap.poll();
            }
            avgs.put(key, sum / size);
        }

        return avgs;
    }
}