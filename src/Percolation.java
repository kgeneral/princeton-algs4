import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.stream.IntStream;

public class Percolation {
	private int N;
	private int virtualTopIndex;
	private int virtualBottomIndex;

	private WeightedQuickUnionUF filter;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		filter = new WeightedQuickUnionUF(N * N + 2);
		virtualTopIndex = N * N;
		virtualBottomIndex = N * N + 1;
		// connect first row to virtaul top
		IntStream.range(0, N).forEach(i -> filter.union(virtualTopIndex, i));

		// connect first row to virtual bottom
		IntStream.range(N * (N - 1), N * N).forEach(i -> filter.union(virtualBottomIndex, i));

	}

	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {

	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		return false;
	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		return false;
	}

	// does the system percolate?
	public boolean percolates() {
		return false;
	}

	// test client (optional)
	public static void main(String[] args) {
		int N = 5;
		Percolation percolation = new Percolation(N);
		percolation.percolates();

	}
}
