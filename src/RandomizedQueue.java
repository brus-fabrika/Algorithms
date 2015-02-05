import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private final int INITAIL_CAPACITY = 5;

    private Item[] q;

    private int head = 0;
    private int tail = 0;

    private int N = 0;

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] qq = (Item[]) new Object[N];
        private int pos = 0;

        public RandomizedQueueIterator() {
            for (int i = 0; i < N; ++i) {
                int index = (head + i) % q.length;
                qq[i] = q[index];

                int r = StdRandom.uniform(i + 1);
                swap(i, r);
            }
        }

        private void swap(int i, int j) {
            Item tmp = qq[i];
            qq[i] = qq[j];
            qq[j] = tmp;
        }

        @Override
        public boolean hasNext() {
            return pos < qq.length;
        }

        @Override
        public Item next() {
            if (pos >= qq.length) {
                throw new java.util.NoSuchElementException();
            }

            return qq[pos++];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[INITAIL_CAPACITY];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        q[tail] = item;

        int c = tail - head + 1;
        if (head > tail) { c += q.length; }
        int r = StdRandom.uniform(c);
        int index = (head + r) % q.length;

        Item tmp = q[index];
        q[index] = q[tail];
        q[tail] = tmp;

        tail = (tail + 1) % q.length;
        N++;

        if (tail == head) {
            int newSize = N * 15 / 10;
            resize(newSize);
        }
    }

    private void resize(int d) {
        Item[] qTemp = (Item[]) new Object[d];

        for (int i = 0; i < N; ++i) {
            int index = (head + i) % q.length;
            qTemp[i] = q[index];
        }

        q = qTemp;

        head = 0;
        tail = N % q.length;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Item value = q[head];
        q[head] = null;

        head = (head + 1) % q.length;
        N--;

        if ((0 < N) && (N < q.length/4)) {
            resize(N);
        }

        return value;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        int index = (head + StdRandom.uniform(N)) % q.length;

        return q[index];
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

   // unit testing
   public static void main(String[] args) {
       RandomizedQueue<String> q = new RandomizedQueue<String>();
       q.enqueue("a");
       q.enqueue("b");
       q.enqueue("c");
       q.enqueue("d");
       q.enqueue("e");

       System.out.println(q.dequeue());
       System.out.println(q.dequeue());
       System.out.println(q.dequeue());
       System.out.println(q.dequeue());
       System.out.println(q.dequeue());

       q.enqueue("dsf");

   }
}