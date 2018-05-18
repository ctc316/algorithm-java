public class Solution {
    /*
     * @param key: A string you should hash
     * @param HASH_SIZE: An integer
     * @return: An integer
     */
    public int hashCode(char[] key, int HASH_SIZE) {
        if (key == null || key.length == 0) {
            return 0;
        }

        long hash = 0;
        for (char c : key) {
            hash = (hash * 33 + (int)c) % HASH_SIZE;
        }

        return (int) hash;
    }
}