public class PercolationStats {

    private int mGridSize;
    private int mExperimentNumber;
    private double[] mExperimentData;
    
    private double mMean;
    private double mStddev = Double.NaN;
    private double mIntervalLo = Double.NaN;
    private double mIntervalHi = Double.NaN;
    
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        mGridSize = n;
        mExperimentNumber = t;
        mExperimentData = new double[mExperimentNumber];

        runExperimentSeries();
    }

    private void runExperimentSeries() {
        for (int i = 0; i < mExperimentNumber; ++i) {
            mExperimentData[i] = runExperiment();
        }
        
        mMean = StdStats.mean(mExperimentData);
        if (mExperimentNumber > 1) {
            mStddev = StdStats.stddev(mExperimentData);
            mIntervalLo = mMean - 1.96*mStddev/Math.sqrt(mExperimentNumber);
            mIntervalHi = mMean + 1.96*mStddev/Math.sqrt(mExperimentNumber);
        }
    }
    
    private double runExperiment() {
        Percolation p = new Percolation(mGridSize);
        int triesCount = 0;
        while (!p.percolates()) {
            int i = StdRandom.uniform(1, mGridSize + 1);
            int j = StdRandom.uniform(1, mGridSize + 1);
            if (!p.isOpen(i, j)) {
                p.open(i, j);
                triesCount++;
            }
        }
        
        return triesCount/(double) (mGridSize*mGridSize);
    }
    
    public double mean() // sample mean of percolation threshold
    {
        return mMean;
    }

    public double stddev() // sample standard deviation of percolation threshold
    {
        return mStddev;
    }

    public double confidenceLo() // low endpoint of 95% confidence interval
    {
        return mIntervalLo;
    }

    public double confidenceHi() // high endpoint of 95% confidence interval
    {
        return mIntervalHi;
    }

    public static void main(String[] args) {
        int N = 2;
        int T = 100000;

        if (args.length == 2) {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        }
        
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo()
                + ", " + ps.confidenceHi());
    }

}
