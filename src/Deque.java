import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node front;
    private Node back;
    private int currentSize;

    private class Node {
        private Item value;
        private Node next;
        private Node prev;

        public Node(Item item) {
            value = item;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = front;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }

            Item item = current.value;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return front == null;
    }

    // return the number of items on the deque
    public int size() {
        return currentSize;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        Node n = new Node(item);
        if (front == null) {
            back = n;
        } else {
            n.next = front;
            front.prev = n;
        }
        front = n;
        currentSize++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        Node n = new Node(item);

        if (back == null) {
            front = n;
        } else {
            back.next = n;
            n.prev = back;
        }
        back = n;
        currentSize++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (front == null) {
            throw new java.util.NoSuchElementException();
        }

        Item i = front.value;
        front = front.next;

        if (front == null) {
            back = null;
        } else {
            front.prev = null;
        }

        currentSize--;
        return i;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (back == null) {
            throw new java.util.NoSuchElementException();
        }

        Item i = back.value;
        back = back.prev;

        if (back == null) {
            front = null;
        } else {
            back.next = null;
        }
        currentSize--;
        return i;
    }

    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }

}
