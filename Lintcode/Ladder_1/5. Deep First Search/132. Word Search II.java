// Version 1: DFS
public class Solution {

    private static final int[][] MOVES = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    char[][] board;
    int n, m;
    Set<String> dict;
    List<String> results;
    boolean[][] visited;


    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return new ArrayList<String>();
        }

        n = board.length;
        m = board[0].length;
        dict = new HashSet<>(words);
        results = new ArrayList<>();

        this.board = board;
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, "", board[i][j], new ArrayList<String>(words));
                visited[i][j] = false;
            }
        }

        return results;
    }

    private void dfs(int row, int col, String lastStr, char ch, List<String> validWords) {

        List<String> nextValids = new ArrayList<>();
        for (String word : validWords) {
            if (!dict.contains(word) ||
                lastStr.length() >= word.length() ||
                word.charAt(lastStr.length()) != ch) {
                continue;
            }

            if (lastStr.length() == word.length() - 1) {
                results.add(word);
                dict.remove(word);
            } else {
                nextValids.add(word);
            }
        }

        if (nextValids.size() == 0) {
            return;
        }

        for (int i = 0; i < MOVES.length; i++) {
            int newR = row + MOVES[i][0];
            int newC = col + MOVES[i][1];

            if (newR < 0 || newR >= n || newC < 0 || newC >= m || visited[newR][newC]) {
                continue;
            }

            visited[newR][newC] = true;
            dfs(newR, newC, lastStr + ch, board[newR][newC], new ArrayList<String>(nextValids));
            visited[newR][newC] = false;
        }
    }
}


// Version 2: Trie