import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static class Node<Item> {
        private Item data;
        private Node<Item> next;
    }
    
    private Node<Item> first;
    private Node<Item> last;
    private int size;
    
    public RandomizedQueue() {
        first = null;
        last = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        checkNullInput(item);
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.data = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldLast.next = last;
        size++;
    }
    
    private void checkNullInput(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }
    
    public Item dequeue() {
        checkIfQueueIsEmpty();
        int randomPosition = StdRandom.uniform(1, size + 1);
        if (randomPosition == 1) {
            removeFirst();
        } else {
            Node<Item> lastNode = getNodeAt(randomPosition - 1);
            Node<Item> removedNode = lastNode.next;
            Item item = removedNode.data;
                lastNode.next = removedNode.next;
                removedNode.next = null;
                
                size--;
                return item; 
        }
        
        throw new NoSuchElementException();
    }
    
    private Node<Item> getNodeAt(int position) {
        Node<Item> lastNode = first;
        int i = 1;
        while (lastNode != null) {
            if (i == position) {
                break;
            }
            i++;
            lastNode = lastNode.next;
        }
        return lastNode;
    }
    
    private void checkIfQueueIsEmpty() {
         if (isEmpty()) {
            throw new NoSuchElementException();
         }
    }
      
    private Item removeFirst() {
       Item item = first.data;
       first = first.next;
       size--;
       
       return item;
    }
    
    public Item sample() {
        checkIfQueueIsEmpty();
        int randomPosition = StdRandom.uniform(1, size + 1);
        Node<Item> lastNode = getNodeAt(randomPosition);
        return lastNode.data;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
    
    private static class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        
        public ListIterator(Node<Item> item) {
            current = item;
        }
        
        @Override 
        public boolean hasNext() {
            return current != null;
        }
        
        @Override 
        public Item next() {
            if (hasNext()) {
                Item item = current.data;
                current = current.next;
                return item;
            }
            throw new NoSuchElementException();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        int sample = queue.sample();
        System.out.print(sample);
    }
}