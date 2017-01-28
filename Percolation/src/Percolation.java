import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
 private WeightedQuickUnionUF grid;
 private boolean[] open;
 private int n; //grid is n*n
 private int total;
 private int openCount;
 
 public static void main(String[] args) {
 //Percolation p = new Percolation(4);
  //System.out.println( p.getIndex(2,2) );
 }
 
 public Percolation(int N){ // create n-by-n grid, with all sites blocked
  if(N <= 0) throw new java.lang.IllegalArgumentException();
  n = N;
  total = n*n;
  grid = new WeightedQuickUnionUF(total); 
  open = new boolean[total];
  for(int i = 0; i < total; i++){
   open[i] = false;
  }
  openCount = 0;
 }
 
 public void open(int row, int col){ // open site (row, col) if it is not open already
  if (row <= 0 || row > n) throw new IndexOutOfBoundsException("row index i out of bounds");
  if (col <= 0 || col > n) throw new IndexOutOfBoundsException("col index i out of bounds");
  if(isOpen(row, col)) return;
  open[getIndex(row, col)] = true;
  openCount++;
  
  int index = getIndex(row, col);
  //connect to neighboring open tiles, using index starting from (1,1)
  int downRow = row + 1, downIndex = getIndex(downRow, col);
  if(downRow <= n){
      if(open[downIndex]) {
          grid.union(index, downIndex);
      }
  }
  int upRow = row - 1, upIndex = getIndex(upRow, col);
  if(upRow >= 1){
      if(open[upIndex]) {
          grid.union(index, upIndex);
      }
  }
  int leftCol = col - 1, leftIndex = getIndex(row, leftCol);
  if(leftCol >= 1){
      if(open[leftIndex]) {
          grid.union(index, leftIndex);
      }
  }
  int rightCol = col + 1, rightIndex = getIndex(row, rightCol);
  if(rightCol <= n){
      if(open[rightIndex]) {
          grid.union(index, rightIndex);
      }
  }
 }
 
 public boolean isOpen(int row, int col){ // is site (row, col) open?
  if (row <= 0 || row > n) throw new IndexOutOfBoundsException("row index i out of bounds");
  if (col <= 0 || col > n) throw new IndexOutOfBoundsException("col index i out of bounds");
  return open[getIndex(row, col)];
 }
 
 public boolean isFull(int row, int col){ // is site (row, col) full? (connected to the top)
  if (row <= 0 || row > n) throw new IndexOutOfBoundsException("row index i out of bounds");
  if (col <= 0 || col > n) throw new IndexOutOfBoundsException("col index i out of bounds");
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
  for(int i=total-n; i<total; i++){
   if(isFull(getRow(i)+1, getCol(i)+1)){
    return true;
   }
  }
  return false;
 }

 private int getIndex(int row, int col){ 
  //takes row and col and returns the equivalent index in a 1D array
  //index starting at 1
  return (row-1)*n + (col-1);
 }
 
 private int getRow(int i){ //index 0
  return i/n;
 }
 
 private int getCol(int i){ //index 0
  return i%n;
 }

}
