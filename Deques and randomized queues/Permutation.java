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
       
       //String[] s = in.readAllStrings();
       RandomizedQueue<String> rq = new RandomizedQueue<String>();
       while (!in.isEmpty()) {
            rq.enqueue(in.readString());
        }
       //if(k >= s.length) k = s.length - 1;
       if(k >= rq.size()) k = rq.size() - 1;
       
       //add elements to queue
       //RandomizedQueue<String> rq = new RandomizedQueue<String>();
       //for(int i = 0; i < s.length; i++){
         //  rq.enqueue(s[i]);
       //}
       
       //iterate and print k elements at random
       Iterator<String> it = rq.iterator();
       for(int i = 0; i < k; i++) {
           StdOut.println(it.next());
           //System.out.println(it.next());
       }
       
    }
    
}