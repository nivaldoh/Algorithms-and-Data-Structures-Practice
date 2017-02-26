import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class Board {
    private int[][] b;
    private int n;
                                            // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {          // (where blocks[i][j] = block in row i, column j) 
        b = blocks;
        n = b.length;
    }
                                           
    public int dimension() {                // board dimension n
        return b.length;
    }
    
    public int hamming() {                  // number of blocks out of place
        int h = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (b[i][j] != (i*n + j + 1)) h++;
            }
        }
        return h - 1; // doesn't count the empty spot (represented by 0)
    }
    
    public int manhattan() {                // sum of Manhattan distances between blocks and goal
        int m = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int current = b[i][j];
                int correct = i*n + j + 1;
                
                if (current == 0) continue; // doesn't count the empty spot (represented by 0)
                if (current == correct) continue;
                
                int goalI, goalJ;
                if(current <= n){
                    goalI = 0;
                    goalJ = current - 1;
                }
                else{
                    if(current%n == 0) goalI = current/n - 1; //integer division
                    else goalI = current/n;
                    goalJ = current%n - 1;
                    if(goalJ < 0) goalJ = n - 1;
                }
                int distI = goalI - i;
                if(distI < 0) distI *= -1;
                int distJ = goalJ - j;
                if(distJ < 0) distJ *= -1;
                //System.out.println("CURRENT = " + current);
                //System.out.println("GOAL I = " + goalI);
                //System.out.println("GOAL J = " + goalJ);
//                System.out.println("DIST I = " + distI);
//                System.out.println("DIST J = " + distJ);
                m += distI + distJ;
                }
            }
        return m;
    }
    
    public boolean isGoal() {               // is this board the goal board?
        return hamming() == 0;
    }
    
    public Board twin() {                   // a board that is obtained by exchanging any pair of blocks
        int[][] t = new int[n][n];
        System.arraycopy( b, 0, t, 0, n );
        if(t[0][0] != 0 && t[0][1] != 0) {
            int temp = t[0][0];
            t[0][0] = t[0][1];
            t[0][1] = temp;
        }
        else{
            int temp = t[1][0];
            t[1][0] = t[1][1];
            t[1][1] = temp;
        }
        return new Board(t);
    }
    
    public boolean equals(Object y) {       // does this board equal y?
        if(y == null) throw new NullPointerException("Attempted to compare a null board");
        return y.toString().equals(toString());
    }
    
    public Iterable<Board> neighbors() {    // all neighboring boards
        return new SomeIterable();
    }
    
    private class SomeIterable implements Iterable<Board> {
        public Iterator<Board> iterator() {
            return new LinkedListIterator();
        }
    }
    
    private class LinkedListIterator implements Iterator<Board>{
        private class Node{
            public Board item;
            public Node next;
            
            public Node (Board bo, Node ne){
                item = bo;
                next = ne;
            }
        }
        
        private Node firstNode;
        private Node currentNode;
        
        public LinkedListIterator() {
            currentNode = null;
            firstNode = null;
            //find the empty space index in the board
            int x = 0, y = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (b[i][j] == 0) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
            
            if(x - 1 >= 0) { 
                int[][] t = new int[n][n];
                for (int i = 0; i < n; i++) { // perform deep copy
                    t[i] = Arrays.copyOf(b[i], n);
                }
                
                swap(t, x, y, x - 1, y);
                Node nd = new Node(new Board(t), null);
                
                if(firstNode == null) { 
                    firstNode = nd;
                    currentNode = nd;
                }
                else{
                    currentNode.next = nd;
                    currentNode = nd;
                }
            }
                
            if(x + 1 < n) { 
                int[][] t = new int[n][n];
                for (int i = 0; i < n; i++) { // perform deep copy
                    t[i] = Arrays.copyOf(b[i], n);
                }
                
                swap(t, x, y, x + 1, y);
                Node nd = new Node(new Board(t), null);
                
                if(firstNode == null) { 
                    firstNode = nd;
                    currentNode = nd;
                }
                else{
                    currentNode.next = nd;
                    currentNode = nd;
                }
            }
            if(y - 1 >= 0) { 
                int[][] t = new int[n][n];
                for (int i = 0; i < n; i++) { // perform deep copy
                    t[i] = Arrays.copyOf(b[i], n);
                }
                
                swap(t, x, y, x, y - 1);
                Node nd = new Node(new Board(t), null);
                
                if(firstNode == null) { 
                    firstNode = nd;
                    currentNode = nd;
                }
                else{
                    currentNode.next = nd;
                    currentNode = nd;
                }
            }
            if(y + 1 < n) { 
                int[][] t = new int[n][n];
                for (int i = 0; i < n; i++) { // perform deep copy
                    t[i] = Arrays.copyOf(b[i], n);
                }
                
                swap(t, x, y, x, y + 1);
                Node nd = new Node(new Board(t), null);
                if(firstNode == null) { 
                    firstNode = nd;
                    currentNode = nd;
                }
                else{
                    currentNode.next = nd;
                    currentNode = nd;
                }
            }
            currentNode = firstNode;
        }
        
        public boolean hasNext() {
            return currentNode != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Board next() {
            if (!hasNext()) throw new NoSuchElementException();
            Board next = currentNode.item;
            currentNode = currentNode.next;
            return next;
        }
        
        private void swap(int[][] a, int fromX, int fromY, int toX, int toY){
            int temp = a[fromX][fromY];
            a[fromX][fromY] = a[toX][toY];
            a[toX][toY] = temp;
        }
    }
    
    public String toString() {              // string representation of this board (in the output format specified below)
        String s = new Integer(n).toString() + '\n';
        for (int i = 0; i < n; i++) { // TODO: improve performance. As it stands, the string gets reconstructed for each character added
            for (int j = 0; j < n; j++) {
                if (j == n - 1) {
                    s += new Integer(b[i][j]).toString() + '\n';
                    continue;
                }
                s += new Integer(b[i][j]) + " ";
            }
        }
        return s;
    }

    public static void main(String[] args) { // unit tests (not graded)
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        StdOut.println(initial.toString());
        System.out.println("Hamming = " + initial.hamming());
        System.out.println("Manhattan = " + initial.manhattan());
        System.out.println("Solved = " + initial.isGoal());
        
        
        for (Board board : initial.neighbors()){
            System.out.println(board.toString());
        }
    }
}