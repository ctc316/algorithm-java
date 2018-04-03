/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

class Pair {
    int distance;
    Point point;
    Pair(Point orig, Point p) {
        distance = (p.x - orig.x) * (p.x - orig.x) + (p.y - orig.y) * (p.y - orig.y); 
        this.point = p;
    }
}

public class Solution {
    /*
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Point[] results = new Point[k];

        if (points == null || points.length < k) {
            return results;
        }

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(k + 1, new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p2.distance == p1.distance) {
                    if (p2.point.x != p1.point.x) {
                        return p2.point.x - p1.point.x;
                    }
                    return p2.point.y - p1.point.y;
                }
                return p2.distance - p1.distance;
            }
        });

        for(Point p : points) {
            Pair pair = new Pair(origin, p);
            maxHeap.offer(pair);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int size = Math.min(maxHeap.size(), k);
        for(int i = 0; i < size; i++) {
            results[size - 1 - i] = maxHeap.poll().point;
        }

        return results;
    }
}