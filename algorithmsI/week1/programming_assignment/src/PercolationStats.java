/**
 *  @author        Gerasimos Athanasopoulos
 *  Login:         ath.ger@gmail.com
 *  Written:       8/20/2012
 *  Last updated:  8/20/2012
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats.java N T
 *
 *  Description:   Generates statistical computations (mean, stdev) for T
 *                 percolation experiments using NxN sites
 */

public class PercolationStats {
    /**
     * This is the confidence coefficient.
     */
    private static final double CF = 1.96;

    /**
     * This is the array that holds the fraction of the
     * percolation threshold open sites.
     */
    private final double[] fracs;

    /**
     * The percolation agent.
     */
    private Percolation perc;

    /**
     * Run a statistical analysis for the NxN grid T times.
     * @param N times N is the grid size
     * @param T number of experiments
     * @return
     */
    public PercolationStats(final int N, final int T) {
        checkArgs(N, T);
        fracs = new double[T];
        for (int i = 0; i < T; i++) {
            perc = new Percolation(N);
            int j = 0;
            while (!perc.percolates()) {
                int x = StdRandom.uniform(1, N + 1);
                int y = StdRandom.uniform(1, N + 1);
                if (!perc.isOpen(x, y)) { j++; }
                perc.open(x, y);
            }
            fracs[i] = j / (double) (N * N);
        }
    }

    /**
     * @return the mean value of percolation threshold
     */
    public double mean() {
        return StdStats.mean(fracs);
    }

    /**
     * @return the standard deviation of the percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(fracs);
    }

    /**
     * Checks for the validity of the arguments.
     * @param N times N is the grid size
     * @param T number of experiments
     * @return
     */
    private void checkArgs(final int N, final int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException(
                    "Arguments must be both positive Integers"
            );
        }
    }

    /**
     * Test client.
     * Convert command-line arguments to integers and perform statistics.
     * @param args command-line arguments
     * @return
     */
    public static void main(final String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        double lconf = stats.mean() - (CF * Math.sqrt(stats.stddev() / T));
        double rconf = stats.mean() + (CF * Math.sqrt(stats.stddev() / T));
        StdOut.printf("mean                    = %.16f%n", stats.mean());
        StdOut.printf("stddev                  = %.16f%n", stats.stddev());
        StdOut.printf("95%% confidence interval = %.16f, %.16f%n",
                lconf, rconf);
    }
}
