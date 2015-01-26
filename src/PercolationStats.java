public class PercolationStats {

    private Percolation p;
    
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int n, int t) {
        if( n <= 0 || t <= 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        
        p = new Percolation(n);
    }

    public double mean() // sample mean of percolation threshold
    {
        return 0.0;
    }

    public double stddev() // sample standard deviation of percolation threshold
    {
        return 0.0;
    }

    public double confidenceLo() // low endpoint of 95% confidence interval
    {
        return 0.0;
    }

    public double confidenceHi() // high endpoint of 95% confidence interval
    {
        return 0.0;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo()
                + ", " + ps.confidenceHi());
    }

}
