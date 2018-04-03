public class Solution {

    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null ||
            start.length() == 0 || start.length() != end.length()) {
            return 0;
        }

        int strLen = start.length();
        dict.add(end);

        Queue<String> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        queue.offer(start);
        hash.add(start);

        int length = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            length++;

            for (int n = 0; n < size; n++) {
                String str = queue.poll();

                // found end
                if (str.equals(end)) {
                    return length;
                }

                // change char 1 at a time
                for (int i = 0; i < strLen; i++) {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        String next = replace(str, i, ch);
                        if (dict.contains(next) && !hash.contains(next)) {
                            queue.offer(next);
                            hash.add(next);
                        }
                    }
                }
            }
        }

        return 0;
    }

    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}