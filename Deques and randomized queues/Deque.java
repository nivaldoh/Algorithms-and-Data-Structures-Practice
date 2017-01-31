import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private int n;
    private Node first_node;
    private Node current_node;
    
    private class Node{
        public Item item;
        public Node next_node;
    }
    
    public Deque() {                          // construct an empty deque
         n = 0;
         first_node = null;
         current_node = null;
    }
    
    public boolean isEmpty() {                // is the deque empty?
        return n ==0;
    }
    
    public int size() {                       // return the number of items on the deque
        return n;
    }
    
    public void addFirst(Item item) {          // add the item to the front
        if(item == null) throw new java.lang.NullPointerException();
    }
    
    public void addLast(Item item) {           // add the item to the end
    
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
            return current_node.next_node != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            i_current_node = i_current_node.next_node;
            return i_current_node.item;
        }
    }
    
    public static void main(String[] args) {   // unit testing (optional)
        
    }
}