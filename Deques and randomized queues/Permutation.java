import java.util.Iterator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    
    public static void main(String[] args) {
       //read input
       int k = Integer.parseInt(args[0]);
       if(k == 0) return;
       In in = new In();
       
       //store input in a queue
       RandomizedQueue<String> rq = new RandomizedQueue<String>();
       while (!in.isEmpty()) {
            rq.enqueue(in.readString());
        }
       if(k >= rq.size()) k = rq.size() - 1;
       
       //iterate and print k elements at random
       Iterator<String> it = rq.iterator();
       for(int i = 0; i < k; i++) {
           StdOut.println(it.next());
       }
       
    }
    
}