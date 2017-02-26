import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Collections;

public class Solver {
    private MinPQ<SearchNode> pq;
    private boolean solved;
    private ArrayList<Board> solutionTree;
    private SearchNode lastNode;
    
    private class SearchNode implements Comparable<SearchNode>{
        public Board board;
        public int priority;
        public SearchNode previous;
        public int moves;
        
        public SearchNode(Board b, int manhattan, SearchNode prev, int m){
            board = b;
            priority = manhattan + m;
            previous = prev;
            moves = m;
        }
        
        public int compareTo(SearchNode other){
            if(priority > other.priority) return 1;
            if(priority < other.priority) return -1;
            return 0;
        }
    }
    
    public Solver(Board initial) {          // find a solution to the initial board (using the A* algorithm)
        if (initial == null) throw new NullPointerException("Null board");
        solutionTree = new ArrayList<Board>();
        if (initial.manhattan() == 0) {
            solutionTree.add(initial);
            solved = true;
            return;
        }
        
        pq = new MinPQ<SearchNode>();
        pq.insert(new SearchNode(initial, initial.manhattan(), null, 0));
        solved = initial.isGoal();
        SearchNode min = null;
        while(!solved) {
            min = pq.delMin();
            
            solved = min.board.isGoal();
            for(Board b : min.board.neighbors()) {
                if(!b.equals(min.board))
                    pq.insert(new SearchNode(b, b.manhattan(), min, min.moves + 1));
            }
        }
        lastNode = min;
        while(min != null) {
            solutionTree.add(min.board);
            min = min.previous;
        }
        Collections.reverse(solutionTree);
    }
    
    public boolean isSolvable() {           // is the initial board solvable?
        return solved;
    }
    
    public int moves() {                    // min number of moves to solve initial board; -1 if unsolvable
        //return solutionTree.size() - 1;
        return lastNode.moves;
    }
    
    public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
        return solutionTree;
    }
    
    
    
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}