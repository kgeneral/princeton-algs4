import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.stream.IntStream;

public class Percolation {
	private int N;
	private int[] filter;
	private int virtualTopIndex;
	private int virtualBottomIndex;

	private WeightedQuickUnionUF weightedQuickUnionUF;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		if (N < 1)
			throw new IllegalArgumentException();

		this.N = N;
		filter = new int[N * N];

		weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 2);
		virtualTopIndex = N * N;
		virtualBottomIndex = N * N + 1;
		// connect first row to virtual top
		IntStream.range(0, N).forEach(i -> weightedQuickUnionUF.union(virtualTopIndex, i));

		// connect first row to virtual bottom
		IntStream.range(N * (N - 1), N * N).forEach(i -> weightedQuickUnionUF.union(virtualBottomIndex, i));
	}

	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		int index = (i - 1) * N + j - 1;
		filter[index] = 1;
		if (j < N && isOpen(i, j + 1))
			weightedQuickUnionUF.union(index, index + 1);
		if (j > 1 && isOpen(i, j - 1))
			weightedQuickUnionUF.union(index, index - 1);
		if (i < N && isOpen(i + 1, j))
			weightedQuickUnionUF.union(index, index + N);
		if (i > 1 && isOpen(i - 1, j))
			weightedQuickUnionUF.union(index, index - N);
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
		return isOpen(i, j) && weightedQuickUnionUF.connected(index, virtualTopIndex);
	}

	// does the system percolate?
	public boolean percolates() {
		return weightedQuickUnionUF.connected(virtualTopIndex, virtualBottomIndex);
	}

	// test client (optional)
	public static void main(String[] args) {
		int N = 5;
		Percolation percolation = new Percolation(N);

		int opened = 0;
		while (!percolation.percolates()) {
			int i = StdRandom.uniform(0, N);
			int j = StdRandom.uniform(0, N);

			StdOut.println(i + "," + j);

			if (percolation.isOpen(i, j))
				continue;

			percolation.open(i, j);
			opened++;
		}

		StdOut.println(N * N + " : " + opened);
	}
}
