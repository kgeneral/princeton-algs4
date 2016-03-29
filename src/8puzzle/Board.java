public class Board {

    private int N;
    private int[][] board;
    private int[][] goal;

    private int[] hamming;
    private int[] manhattan;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        board = new int[N][N];
        goal = new int[N][N];
        hamming = new int[N * N - 1];
        manhattan = new int[N * N - 1];

        String[] index = new String[N * N - 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = blocks[i][j];
                goal[i][j] = i * N + j + 1;

                if (board[i][j] != 0)
                    index[blocks[i][j] - 1] = i + "," + j;
            }
        }
        goal[N - 1][N - 1] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (board[i][j] == 0) continue;

                if (board[i][j] != goal[i][j])
                    hamming[i * N + j] = 1;

                int target = goal[i][j];

                if (goal[i][j] == 0) continue;

                int row = Integer.parseInt(index[target - 1].split(",")[0]);
                int column = Integer.parseInt(index[target - 1].split(",")[1]);

                manhattan[i * N + j] = Math.abs(i - row) + Math.abs(j - column);

            }
        }

        dimension();
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < this.hamming.length; i++)
            hamming += this.hamming[i];
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < this.manhattan.length; i++)
            manhattan += this.manhattan[i];
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        return null;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        String print = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                print += board[i][j] + " ";
            }
            print += "\n";
        }
        return print;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}