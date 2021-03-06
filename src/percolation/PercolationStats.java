import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int N;
    private int T;
    private Percolation percolation;

    private double[] simulatedRatios;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        this.N = N;
        this.T = T;

        simulatedRatios = new double[T];

        for (int i = 0; i < T; i++)
            simulatedRatios[i] = (double) simulate() / (N * N);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(simulatedRatios);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(simulatedRatios);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    private int simulate() {
        int opened = 0;
        percolation = new Percolation(N);
        while (!percolation.percolates()) {
            int i = StdRandom.uniform(1, N + 1);
            int j = StdRandom.uniform(1, N + 1);
            if (percolation.isOpen(i, j))
                continue;
            percolation.open(i, j);
            opened++;
        }
        return opened;
    }

    // test client (described below)
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();

        PercolationStats percolationStats = new PercolationStats(N, T);

        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = " + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi());
    }
}
