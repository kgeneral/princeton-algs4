import java.util.ArrayList;
import java.util.List;

public class Board {

    private int N;
    private int[][] board;

    private int hamming;
    private int manhattan;

    private int blankSquareRow;
    private int blankSquareColumn;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = blocks[i][j];
                int target = board[i][j];

                if (target != 0) {
                    int goal = i * N + j + 1;
                    if (goal >= N * N) goal = 0;

                    if (target != goal) {
                        hamming += 1;
                        int row = (target - 1) / N;
                        int column = (target - 1) % N;
                        manhattan += Math.abs(i - row) + Math.abs(j - column);
                    }
                } else {
                    blankSquareRow = i;
                    blankSquareColumn = j;
                }
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
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
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

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board[i][j] != comparer.board[i][j]) return false;

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