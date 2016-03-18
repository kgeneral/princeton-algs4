import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int[] filter;
    private int virtualTopIndex;
    private int virtualBottomIndex;

    private WeightedQuickUnionUF unionFindForUpwardConnectivity;
    private WeightedQuickUnionUF unionFindForGlobal;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N < 1)
            throw new IllegalArgumentException();

        this.N = N;
        filter = new int[N * N];

        unionFindForGlobal = new WeightedQuickUnionUF(N * N + 2);
        unionFindForUpwardConnectivity = new WeightedQuickUnionUF(N * N + 1);
        virtualTopIndex = N * N;
        virtualBottomIndex = N * N + 1;
        // connect first row to virtual top
        for (int i = 0; i < N; i++)
            union(virtualTopIndex, i);

        // connect first row to virtual bottom
        for (int i = N * (N - 1); i < N * N; i++)
            unionFindForGlobal.union(virtualBottomIndex, i);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        int index = (i - 1) * N + j - 1;
        filter[index] = 1;
        if (j < N && isOpen(i, j + 1))
            union(index, index + 1);
        if (j > 1 && isOpen(i, j - 1))
            union(index, index - 1);
        if (i < N && isOpen(i + 1, j))
            union(index, index + N);
        if (i > 1 && isOpen(i - 1, j))
            union(index, index - N);
    }

    private void union(int p, int q) {
        unionFindForGlobal.union(p, q);
        unionFindForUpwardConnectivity.union(p, q);
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        int index = (i - 1) * N + j - 1;
        if (index < 0 || index >= N * N)
            throw new IndexOutOfBoundsException();
        return filter[index] == 1;
    }

    // is site (row i, column j) full? - opened and connected with top
    public boolean isFull(int i, int j) {
        int index = (i - 1) * N + j - 1;
        return isOpen(i, j) && unionFindForGlobal.connected(index, virtualTopIndex)
                && unionFindForUpwardConnectivity.connected(index, virtualTopIndex);
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFindForGlobal.connected(virtualTopIndex, virtualBottomIndex);
    }

    // test client (optional)
    public static void main(String[] args) {
        int N = 5;
        Percolation percolation = new Percolation(N);

        int opened = 0;
        while (!percolation.percolates()) {
            int i = StdRandom.uniform(1, N + 1);
            int j = StdRandom.uniform(1, N + 1);

            StdOut.println(i + "," + j);

            if (percolation.isOpen(i, j))
                continue;

            percolation.open(i, j);
            opened++;
        }

        StdOut.println(N * N + " : " + opened);
    }
}
