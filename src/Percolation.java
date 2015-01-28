public class Percolation {

    private WeightedQuickUnionUF gridPercolationData;
    private boolean [] grid;
    private final int N;
    
    /** create N-by-N grid, with all sites blocked.
     * @param n - grid dimension
     * */
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        
        N = n;
        gridPercolationData = new WeightedQuickUnionUF(N * N + 2);
        grid = new boolean[N * N + 2];

        grid[0] = true;
        grid[grid.length - 1] = true;
    }

    /** open site (row i, column j) if it is not open already. */
    public void open(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        final int index = convertIndex(i, j);

        grid[index] = true;

        if (i == 1) {
            gridPercolationData.union(index, 0);
        }

        if (i == N) {
            gridPercolationData.union(index, N*N + 1);
        }

        if (i > 1 && isOpen(i - 1, j)) { gridPercolationData.union(index, index-N); }
        
        if (i < N && isOpen(i + 1, j)) { gridPercolationData.union(index, index+N); }
        
        if (j > 1 && isOpen(i, j - 1)) { gridPercolationData.union(index, index-1); }

        if (j < N && isOpen(i, j + 1)) { gridPercolationData.union(index, index+1); }

    }

    /** is site (row i, column j) open? */
    public boolean isOpen(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        return grid[convertIndex(i, j)];
    }

    /** is site (row i, column j) full? */
    public boolean isFull(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        
        final int index = convertIndex(i, j);
        return gridPercolationData.connected(index, 0);
    }

    /** does the system percolate?
     * @return true is grid percolates
     * */
    public boolean percolates() {
        return gridPercolationData.connected(0, N*N + 1);
    }

    private int convertIndex(int i, int j) {
        return j + (i - 1) * N;
    }
}
