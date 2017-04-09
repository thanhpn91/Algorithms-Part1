import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;
    
    private static class Node<Item> {
        private Item data;
        private Node<Item> next;
    }
    
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }
    
    public void addFirst(Item item) {
        checkNullInput(item);
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.data = item;
        if (last == null) last = first;
        else            first.next = oldFirst;
        size++;
    }
    
    public void addLast(Item item) {
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
    
    public Item removeFirst() {
       checkIfQueueIsEmpty();
       Item item = first.data;
       first = first.next;
       size--;
       
       return item;
    }
    
    public Item removeLast() {
        checkIfQueueIsEmpty();
        Node<Item> traverseNode = first;
        for (int i = 0; i < size; i++) {
            if (traverseNode.next == last) {
                Item item = last.data;
                last = traverseNode;
                last.next = null;
                size--;
                
                return item;
            }
            traverseNode = traverseNode.next;
        }
        throw new NoSuchElementException();
    }
    
    private void checkIfQueueIsEmpty() {
        if (isEmpty()) {
           throw new NoSuchElementException();
       }
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return size;
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
        Deque<Integer> newQueue = new Deque<Integer>(); 
        System.out.println(newQueue.size());
        newQueue.addFirst(1);
        newQueue.addLast(4);
        newQueue.addLast(5);
        newQueue.addLast(6);
        newQueue.removeFirst();
        newQueue.removeLast();
        System.out.println(newQueue.size());
    }
}