import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Node first_node;
    private Node last_node;
    
    private class Node{
        public Item item;
        public Node next_node;
        public Node prev_node;
    }
    
    public RandomizedQueue() {                // construct an empty randomized queue
        n = 0;
        first_node = null;
        last_node = null;
    }
    
    public boolean isEmpty() {                 // is the queue empty?
        return n == 0;
    }
    
    public int size() {                        // return the number of items on the queue
        return n;
    }
    
    public void enqueue(Item item) {           // add the item
        if(item == null) throw new java.lang.NullPointerException();
        Node node = new Node();
        node.item = item;
        if(n == 0) {
            first_node = node;
            first_node.prev_node = null;
            first_node.next_node = null;
            last_node = first_node;
            n++;
            return;
        }
        
        if(n == 1){
            node.next_node = null;
            node.prev_node = first_node;
            first_node.next_node = node;
            last_node = node;
            n++;
            return;
        }
        
        //Append item to the end of the linked list
        node.next_node = null;
        node.prev_node = last_node;
        last_node.next_node = node;
        last_node = node;
        n++;
    }
    
    public Item dequeue() {                    // remove and return a random item
        if(n == 0) throw new NoSuchElementException();
        if(n == 1){
            Item item = first_node.item;
            n = 0;
            first_node = null;
            last_node = null;
            return item;
        }
        //go to a random node
        int r = StdRandom.uniform(n);
        Node node = first_node;
        for(int i = 0; i < r; i++){
            node = node.next_node;
        }
        
        
        
        //remove the random node from the queue and return its item
        Node previous = node.prev_node;
        Node next = node.next_node;
        if(previous != null) previous.next_node = next;
        if(previous == null){
            if(next == null) throw new java.lang.NullPointerException();
            first_node = next;
        }
        if(next != null) next.prev_node = previous;
        n--;
        if(n == 1 && previous != null){
            previous.prev_node = null;
            previous.next_node = null;
            first_node = previous;
            last_node = previous;
        }
        else if (n == 1 && next != null){
            next.prev_node = null;
            next.next_node = null;
            first_node = next;
            last_node = next;                
        }
        Item it = node.item;
        node = null;
        return it;
    }
    
    public Item sample() {                     // return (but do not remove) a random item
        if(n == 0) throw new NoSuchElementException();
        int r = StdRandom.uniform(n);
        Node node = first_node;
        for(int i = 0; i < r; i++){
            node = node.next_node;
        }
        return node.item;
    }
    
    public Iterator<Item> iterator() {         // return an independent iterator over items in random order
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item>{
        private Node[] a;
        private int i;
        private Node i_first_node;
        private Node i_current_node;
        private int count;
        
        public ArrayIterator(){ //TODO: fix iterator data structure being separate from the real one
            i = 0;
            count = n;
            if(n == 0) {
                i_first_node = null;
                i_current_node = null;
                return;
            }
            if(n == 1) {
                i_first_node = first_node;
                i_current_node = i_first_node;
                return;
            }
            //pass linked list to array
            a = (RandomizedQueue<Item>.Node[]) new RandomizedQueue.Node[n];
            Node node = first_node;
            int temp_index = 0;
            while(node != null){
                a[temp_index] = node;
                node = node.next_node;
                temp_index++;
            }
            //shuffle node order
            StdRandom.shuffle(a);
            //turn array into a shuffled linked list
//            for(int j = 0; j < n; j++) {
//                if(j == 0) {
//                    i_first_node = a[0];
//                    i_first_node.prev_node = null;
//                    if(n == 1){
//                        i_first_node.next_node = null;
//                    }
//                    else i_first_node.next_node = a[1];
//                    continue;
//                }
//                if(j == n - 1) {
//                    a[j].prev_node = a[j - 1];
//                    a[j].next_node = null;
//                    continue;
//                }
//                a[j].prev_node = a[j - 1];
//                a[j].next_node = a[j + 1];
//            }
//            i_current_node = i_first_node;
            
            
            //a[] = null //free memory?
        }
        
        public boolean hasNext() {
            //return i_current_node != null;
            return i < count;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
//            Item item = i_current_node.item;
//            //System.out.println(i_current_node.prev_node);
//            i_current_node = i_current_node.next_node;
//            return item;
            
            Item item = a[i].item;
            i++;
            return item;
        }
    }
    
    public static void main(String[] args) {   // unit testing (optional)
        RandomizedQueue<Double> rq = new RandomizedQueue<Double>();
        
        
        rq.enqueue(0.1);
        rq.enqueue(0.7);
        rq.enqueue(0.0);
        rq.enqueue(0.1);
        rq.enqueue(0.1);
        
        //rq.dequeue();
        
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        
        
       Iterator it = rq.iterator();
       while(it.hasNext()){
            System.out.println(it.next());
            //System.out.println(rq.dequeue());
            //it.next();
       }
    }
}