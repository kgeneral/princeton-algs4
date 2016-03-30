import java.util.ArrayList;
import java.util.List;

public class Board {

    private int N;
    private int[][] board;

    private int[] hamming;
    private int[] manhattan;

    private int blankSquareRow;
    private int blankSquareColumn;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        board = new int[N][N];
        hamming = new int[N * N];
        manhattan = new int[N * N];

        String[] index = new String[N * N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = blocks[i][j];

                if (board[i][j] != 0)
                    index[blocks[i][j]] = i + "," + j;
                else {
                    blankSquareRow = i;
                    blankSquareColumn = j;
                }

            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (board[i][j] != 0)
                    if (board[i][j] != i * N + j + 1)
                        hamming[i * N + j] = 1;

                int target = i * N + j + 1;

                if (i == N - 1 && j == N - 1) continue;

                int row = Integer.parseInt(index[target].split(",")[0]);
                int column = Integer.parseInt(index[target].split(",")[1]);

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
        if (!(y instanceof Board)) return false;
        Board comparer = (Board) y;

        if (comparer.dimension() != N) return false;

        if (comparer.hamming() != hamming()) return false;

        if (comparer.manhattan() != manhattan()) return false;

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int[][] neighbor = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                neighbor[i][j] = board[i][j];
            }
        }

        List<Board> neighbors = new ArrayList<>();
        if (blankSquareRow < N - 1) {
            swapBlankSquareRow(neighbor, 1);
            neighbors.add(new Board(neighbor));
            swapBlankSquareRow(neighbor, 1);
        }
        if (blankSquareRow > 0) {
            swapBlankSquareRow(neighbor, -1);
            neighbors.add(new Board(neighbor));
            swapBlankSquareRow(neighbor, -1);
        }
        if (blankSquareColumn < N - 1) {
            swapBlankSquareColumn(neighbor, 1);
            neighbors.add(new Board(neighbor));
            swapBlankSquareColumn(neighbor, 1);
        }
        if (blankSquareColumn > 0) {
            swapBlankSquareColumn(neighbor, -1);
            neighbors.add(new Board(neighbor));
            swapBlankSquareColumn(neighbor, -1);
        }

        return neighbors;
    }

    private void swapBlankSquareColumn(int[][] neighbor, int column) {
        int temp = neighbor[blankSquareRow][blankSquareColumn];
        neighbor[blankSquareRow][blankSquareColumn] = neighbor[blankSquareRow][blankSquareColumn + column];
        neighbor[blankSquareRow][blankSquareColumn + column] = temp;
    }

    private void swapBlankSquareRow(int[][] neighbor, int row) {
        int temp = neighbor[blankSquareRow][blankSquareColumn];
        neighbor[blankSquareRow][blankSquareColumn] = neighbor[blankSquareRow + row][blankSquareColumn];
        neighbor[blankSquareRow + row][blankSquareColumn] = temp;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        String print = N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                print += " " + board[i][j] + " ";
            }
            print += "\n";
        }
        return print;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}