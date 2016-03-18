import java.util.stream.IntStream;

public class Percolation {
	int N;
	int[][] filter;
	int top = -1;
	int bottom = -2;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		this.N = N;
		filter = new int[N][N];
		IntStream.range(0, N).forEach(i -> IntStream.range(0, N).forEach(j -> filter[i][j] = i * N + j));
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

	public void print() {
		IntStream.range(0, N).forEach(
				i -> IntStream.range(0, N).forEach(j -> System.out.println(i + "," + j + " = " + filter[i][j])));
	}

	// test client (optional)
	public static void main(String[] args) {
		int N = 5;
		Percolation percolation = new Percolation(N);
		percolation.print();

	}
}
