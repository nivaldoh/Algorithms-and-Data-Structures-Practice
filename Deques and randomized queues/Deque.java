import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private int n;
    private Node first_node;
    private Node last_node;
    
    private class Node{
        public Item item;
        public Node next_node;
    }
    
    public Deque() {                          // construct an empty deque
         n = 0;
         first_node = null;
         last_node = null;
    }
    
    public boolean isEmpty() {                // is the deque empty?
        return n == 0;
    }
    
    public int size() {                       // return the number of items on the deque
        return n;
    }
    
    public void addFirst(Item item) {          // add the item to the front
        if(item == null) throw new java.lang.NullPointerException();
        if(last_node == null) last_node = first_node;
        Node previous_first = first_node; //TODO: check for mutations
        first_node = new Node();
        first_node.item = item;
        first_node.next_node = previous_first;
    }
    
    public void addLast(Item item) {           // add the item to the end
        if(item == null) throw new java.lang.NullPointerException();
        if(last_node == null) first_node = last_node;
        Node to_add = new Node();
        to_add.item = item;
        to_add.next_node = null;
        last_node.next_node = to_add;
        last_node = to_add;
    }
    
    public Item removeFirst(){                // remove and return the item from the front
        return (Item) new Object();
    }
    
    public Item removeLast() {                 // remove and return the item from the end
        return (Item) new Object();
    }
  
    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
       return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator<Item>{
        //private int i;
        private Node i_current_node = first_node; // TODO: check for mutations
        
        public boolean hasNext() {
            //return i < n;
            return i_current_node != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item next = i_current_node.item;
            i_current_node = i_current_node.next_node;
            return next;
        }
    }
    
    public static void main(String[] args) {   // unit testing (optional)
        Deque<String> d = new Deque<String>();
        d.addFirst("FIRST 1");
        d.addFirst("FIRST 2");
        d.addLast("LAST 1");
        d.addLast("LAST 2");
        d.addFirst("FIRST 3");
        d.addLast("LAST 3");
        Iterator it = d.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}