import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private int n, openCount, t;
    private double[] results;
    
    public static void main(String[] args){
        PercolationStats ps = new PercolationStats(300, 300);
        System.out.println("Mean = " + ps.mean());
        System.out.println("Std Dev = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
   }
    
    public PercolationStats(int N, int trials){    // perform trials independent experiments on an n-by-n grid
        if(N <=0 || trials <= 0) throw new java.lang.IllegalArgumentException();
        n = N;
        t = trials;
        int row, col; //row and col start from 1. (1,1) is the first tile
        results = new double[trials];
        for(int t=0; t<trials; t++){
            Percolation p = new Percolation(n);
            openCount = 0;
            while(!p.percolates()){
                row = StdRandom.uniform(1, n+1);
                col = StdRandom.uniform(1, n+1);
                while(p.isOpen(row, col)){
                    row = StdRandom.uniform(1, n+1);
                    col = StdRandom.uniform(1, n+1);
                }
                p.open(row, col);
                openCount++;
            }
            results[t] = (double)openCount/((double)n*n);
        }
        
   }
    public double mean(){   // sample mean of percolation threshold
        double sum = 0;
        for(int i = 0; i < t; i++){
            sum += results[i];
        }
        return sum/t;
   }
    public double stddev(){                        // sample standard deviation of percolation threshold
       return StdStats.stddev(results);
   }
    public double confidenceLo(){                  // low  endpoint of 95% confidence interval
        return mean() - 1.96d*stddev()/Math.sqrt(t);
   }
    public double confidenceHi(){                  // high endpoint of 95% confidence interval
        return mean() + 1.96d*stddev()/Math.sqrt(t);
   }

    
}