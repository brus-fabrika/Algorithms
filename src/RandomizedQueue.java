import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomizedQueueIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        return false;
    }
    
    // return the number of items on the queue
    public int size() {
        return 0;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
    }
    
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        
        return null;
    }
    
    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        
        return null;
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

   // unit testing
   public static void main(String[] args) {
       
   }
}