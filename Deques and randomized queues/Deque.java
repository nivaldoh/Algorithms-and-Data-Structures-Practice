import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private int n;
    private Node first_node;
    private Node last_node;
    
    private class Node{
        public Item item;
        public Node next_node;
        public Node prev_node;
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
        if(previous_first != null) previous_first.prev_node = first_node;
        first_node.prev_node = null;
        n++;
    }
    
    public void addLast(Item item) {           // add the item to the end
        if(item == null) throw new java.lang.NullPointerException();
        
        Node to_add = new Node();
        to_add.item = item;
        to_add.next_node = null;
        
        if(n == 0) {
            to_add.prev_node = null;
            first_node = to_add;
            last_node = to_add;
            n++;
            return;
        }
        if(n == 1 || last_node == null){ //first node is expected not to be null here
            if(first_node == null) throw new java.lang.NullPointerException();
            to_add.prev_node = first_node;
            first_node.next_node = to_add;
            last_node = to_add;
            n++;
            return;
        }
        
        to_add.prev_node = last_node;
        last_node.next_node = to_add;
        last_node = to_add;
        n++;
    }
    
    public Item removeFirst(){                // remove and return the item from the front
        if(first_node == null) throw new java.lang.NullPointerException();
        if(n == 1){
            Item item = first_node.item;
            first_node = null;
            last_node = null;
            n--;
            return item;
        }
        
        Node previous_first = first_node;
        first_node = previous_first.next_node;
        first_node.prev_node = null;
        n--;

        if(n == 1){
            last_node.prev_node = null;
            first_node = last_node;
        }
        return previous_first.item;
    }
    
    public Item removeLast() {                 // remove and return the item from the end
        if(last_node == null) throw new java.lang.NullPointerException();
        if(n == 1){
            if(first_node == null) throw new java.lang.NullPointerException();
            Item item = first_node.item;
            first_node = null;
            last_node = null;
            n--;
            return item;
        }
        
        Item item = last_node.item;
        Node second_last = last_node.prev_node;
        second_last.next_node = null;
        last_node = second_last;
        n--;
        return item;
    }
  
    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
       return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator<Item>{
        private Node i_current_node = first_node; // TODO: check for mutations
        
        public boolean hasNext() {
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
    
    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        d.addFirst("FIRST 1");
        d.addFirst("FIRST 2");
        d.addLast("LAST 1");
        d.addLast("LAST 2");
        d.addFirst("FIRST 3");
        d.addLast("LAST 3");
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.removeLast();
        d.removeFirst();
        d.removeFirst();
        d.addFirst("FIRST 4");
        d.addLast("LAST 4");
        d.removeLast();
        d.removeLast();
        d.addLast("LAST 5");
        d.addFirst("FIRST 5");
        d.removeFirst();
        d.removeLast();
        
        d.addFirst("FIRST 1");
        d.addFirst("FIRST 2");
        d.addLast("LAST 1");
        d.addLast("LAST 2");
        d.addFirst("FIRST 3");
        d.addLast("LAST 3");
        d.addFirst("FIRST 4");
        d.addLast("LAST 4");
        d.addLast("LAST 5");
        d.addFirst("FIRST 5");
        
        Iterator it = d.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}