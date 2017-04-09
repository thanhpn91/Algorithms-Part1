import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int size;
    private int trialTimes;
    private double[] total;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        
        size = n;
        trialTimes = trials;
        total = new double[trialTimes];
    }
    
    public double mean() {
        for (int i = 0; i < trialTimes; i++) {
           Percolation percolation = new Percolation(size);
           double p = 0;
            while (!percolation.percolates()) {
               int randomRow = StdRandom.uniform(1, size + 1);
               int randomCol = StdRandom.uniform(1, size + 1);
               if (!percolation.isOpen(randomRow, randomCol)) {
                   percolation.open(randomRow, randomCol);
                   p++;
               }
           } 
           total[i] = p/(size*size);
        }
        return StdStats.mean(total);
    }
    
    public double stddev() {
        return StdStats.stddev(total);
    }
    
    public double confidenceLo() {
        return StdStats.mean(total) - ((1.96*stddev())/Math.sqrt(total.length));
    }
    
    public double confidenceHi() {
        return StdStats.mean(total) + ((1.96*stddev())/Math.sqrt(total.length));
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); 
        int t = Integer.parseInt(args[1]); 
        PercolationStats stats = new PercolationStats(n, t);
        double mean = stats.mean();
        double stddev = stats.stddev();
        System.out.printf("mean                    = %f\n", mean);
        System.out.printf("stddev                  = %f\n", stddev);
        System.out.printf("95%% confidence interval = [%f, %f]\n", mean - stddev, mean + stddev);
    }
}  