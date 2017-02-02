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
        Node node = new Node();
        node.item = item;
        if(n == 0) {
            node.next_node = null;
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
        
        n--;
        
        return (Item) new Object();
    }
    
    public Item sample() {                     // return (but do not remove) a random item
        return (Item) new Object();
    }
    
    public Iterator<Item> iterator() {         // return an independent iterator over items in random order
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item>{ //iterator uses an array for linear time constructor
        private Item[] a;
        private int i;
        
        public ArrayIterator(){
            i = 0;
            a = (Item[]) new Object[n];
            Node n = first_node;
            int temp_index = 0;
            while(n != null){
                a[temp_index] = n.item;
                n = n.next_node;
                temp_index++;
            }
            StdRandom.shuffle(a);
            //for(int i = 0; i < n; i++){
                //System.out.println(a[0]);
            //}
        }
        
        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = a[i];
            i++;
            return item;
        }
        
    }
    
    public static void main(String[] args) {   // unit testing (optional)
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        rq.enqueue("4");
        rq.enqueue("5");
        
        Iterator it = rq.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}