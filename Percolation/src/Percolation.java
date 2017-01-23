import edu.princeton.cs.algs4.WeightedQuickUnionUF;;

public class Percolation {
	public WeightedQuickUnionUF grid;
	boolean[] open;
	int n;
	int openCount;
	
	public static void main(String[] args) {
		Percolation p = new Percolation(4);
		//System.out.println( p.grid.find(0) );
	}
	
	public Percolation(int N){ // create n-by-n grid, with all sites blocked
		n = N;
		grid = new WeightedQuickUnionUF(n*n); 
		open = new boolean[n*n];
		for(int i=0; i<n*n; i++){
			open[i] = false;
		}
		openCount = 0;
	}
	
	public void open(int row, int col){ // open site (row, col) if it is not open already
		open[getIndex(row, col)] = true;
		openCount++;
	}
	
	public boolean isOpen(int row, int col){ // is site (row, col) open?
		return open[getIndex(row, col)];
	}
	
	//this is probably wrong
	public boolean isFull(int row, int col){ // is site (row, col) connected to the top?
		if(!open[getIndex(row, col)]) return false;
		int q = getIndex(row, col);
		for(int i=0; i<n; i++){
			if(grid.connected(i, q)){
				return true;
			}
		}
		return false;
	}
	
	public int numberOfOpenSites(){ // number of open sites
		return openCount;
	}
	
	public boolean percolates(){ // does the system percolate?
		grid. 
		return true;
	}
	
	private int getIndex(int row, int col){ 
		//takes row and col and returns the equivalent index in a 1D array
		
		//index starting at 1
		//return (row-1)*n + col; ?
		
		//index starting at 0
		return row*n + col;
	}

}
