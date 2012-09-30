import java.util.Iterator;

/**
 *  @author        Gerasimos Athanasopoulos.
 *  Login:         ath.ger@gmail.com
 *  Written:       8/20/2012
 *  Last updated:  8/20/2012
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation.java
 *
 *  Description:   Checks if a system percolates
 */


public class Percolation {
    /**
     * This is the to master node.
     */
    private static final int MASTER = 0;

    /**
     * The count of the one side of the grid.
     */
    private final int n;

    /**
     * The count of all the sites of the grid.
     */
    private final int size;

    /**
     * The array that holds if a site is open.
     */
    private final boolean[] open;

    /**
     * The weighted quick union agent.
     */
    private final WeightedQuickUnionUF uf;

    /**
     * Holds whether the system has percolated.
     */
    private boolean percolated;


    /**
     * Create N-by-N grid, with all sites blocked.
     * @param N times N is the grid size
     * @return
     */
    public Percolation(final int N) {
        percolated = false;
        n          = N;
        size       = n * n;
        open       = new boolean[size + 1];
        uf         = new WeightedQuickUnionUF(size + 1);
    }

    /**
     * Opens a site (row i, column j) if it is not already opened.
     * @param i the first coordinate
     * @param j the second coordinate
     * @return
     */
    public void open(final int i, final int j) {
        if (!isOpen(i, j)) {
            openSite(i, j);
            connectWithMaster(i, j);
            connectWithAdjacent(i, j);
        }

        // Check bottom row to see if the system has percolated
        // only if the current site is full and not percolated.
        if (!percolated && isFull(i, j)) {
            for (int c = 1; c <= n; c++) {
                if (isFull(n, c)) {
                    percolated = true;
                }
            }
        }
    }

    /**
     * @param i the first coordinate
     * @param j the second coordinate
     * @return true if site (row i, column j) is open
     */
    public boolean isOpen(final int i, final int j) {
        checkIndices(i, j);
        return open[toIndex(i, j)];
    }

    /**
     * @param i the first coordinate
     * @param j the second coordinate
     * @return true if site (row i, column j) is full
     */
    public boolean isFull(final int i, final int j) {
        checkIndices(i, j);
        return isOpen(i, j) && uf.connected(0, toIndex(i, j));
    }

    /**
     * @return true if the system percolates
     */
    public boolean percolates() {
        return percolated;
    }

    /**
     * Connects two nodes of the grid.
     * @param i1 the first coordinate of the first node
     * @param j1 the second coordinate of the first node
     * @param i2 the first coordinate of the second node
     * @param j2 the second coordinate of the second node
     * @return
     */
    private void connect(final int i1, final int j1,
                         final int i2, final int j2) {
        if (validCoordinates(i1, j1)
                && validCoordinates(i2, j2) && isOpen(i2, j2)) {
            uf.union(toIndex(i1, j1), toIndex(i2, j2));
        }
    }

    /**
     * Connects with open adjacent nodes.
     * @param i the first coordinate
     * @param j the second coordinate
     * @return
     */
    private void connectWithAdjacent(final int i, final int j) {
        connect(i, j, i - 1,   j);
        connect(i, j,   i  , j - 1);
        connect(i, j, i + 1,   j);
        connect(i, j,   i  , j + 1);
    }

    /**
     * Connects with master node if in the upper row.
     * @param i the first coordinate
     * @param j the second coordinate
     * @return
     */
    private void connectWithMaster(final int i, final int j) {
        if (i == 1) { uf.union(MASTER, toIndex(i, j)); }
    }

    /**
     * Opens a site.
     * @param i the first coordinate
     * @param j the second coordinate
     * @return
     */
    private void openSite(final int i, final int j) {
        open[toIndex(i, j)] = true;
    }

    /**
     * Maps grid coordinates to union-find array index.
     * @param i the first coordinate
     * @param j the second coordinate
     * @return the union-find array index
     */
    private int toIndex(final int i, final int j) {
        return (i - 1) * n + j;
    }

    /**
     * Checks whether an Integer is in within permitted region.
     * @param i the first coordinate
     * @param j the second coordinate
     * @return
     */
    private void checkIndices(final int i, final int j) {
        if (!validCoordinates(i, j)) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
    }

    /**
     * @param i the first coordinate
     * @param j the second coordinate
     * @return true if the coordinates given are valid
     */
    private boolean validCoordinates(final int i, final int j) {
        return (i > 0 && j > 0 && i <= n && j <= n);
    }
}
