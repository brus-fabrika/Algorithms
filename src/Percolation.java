public class Percolation {

    private WeightedQuickUnionUF gridPercolationData;
    private boolean [] grid;
    private final int N;
    
    /** create N-by-N grid, with all sites blocked.
     * @param n - grid dimension
     * */
    public Percolation(final int n) {
        N = n;
        gridPercolationData = new WeightedQuickUnionUF(N + 2);
        grid = new boolean[N * N + 2];
        
        grid[0] = true;
        grid[1] = true;
        
        for (int i = 1; i < grid.length - 1; ++i) {
            grid[i] = false;
        }
    }

    /** open site (row i, column j) if it is not open already. */
    public final void open(int i, int j) {
        if(i < 1 || j < 1 || i > N || j > N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        
        final int index = i + (j - 1) * N;
        
        grid[index] = true;
        
        if( isOpen(i - 1, j) ) { gridPercolationData.union(index, index-1); }
        if( isOpen(i + 1, j) ) { gridPercolationData.union(index, index+1); }
        if( isOpen(i, j - 1) ) { gridPercolationData.union(index, index-N); }
        if( isOpen(i, j + 1) ) { gridPercolationData.union(index, index-N); }
        
    }

    /** is site (row i, column j) open? */
    public final boolean isOpen(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        return grid[i + (j - 1) * N];
    }

    /** is site (row i, column j) full? */
    public final boolean isFull(int i, int j) {
        final int index = i + (j - 1) * N;
        return gridPercolationData.connected(index, 0)
                && gridPercolationData.connected(index, N + 1);
    }

    /** does the system percolate?
     * @return true is grid percolates
     * */
    public final boolean percolates() {
        return gridPercolationData.connected(0, N + 1);
    }

    public static void main(String[] args) // test client (optional)
    {
    }

}
