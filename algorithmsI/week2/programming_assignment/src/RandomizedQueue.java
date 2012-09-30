import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  @author        Gerasimos Athanasopoulos.
 *  Login:         ath.ger@gmail.com
 *  Written:       8/30/2012
 *  Last updated:  8/30/2012
 *
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue.java
 *
 *  Description:   Implementation of a randomized queue
 */


public class RandomizedQueue<Item> implements Iterable<Item> {

    /**
     * The number of elements in the queue
     */
    private int N;

    /**
     * The array holding the items.
     */
    private Item[] a;

    /**
     * Constructs an empty randomized queue.
     * @return
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }


    /**
     * Checks if the randomized queue is empty.
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Calculates the number of items in the randomized queue.
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * Inserts an item to the randomized queue.
     * @param item is the item to be inserted
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == a.length) { resize(2*a.length); }
        a[N++] = item;
    }

    /**
     * Delete and return a random item of the randomized queue.
     * @return a random item.
     */
    public Item dequeue() {
        checkEmpty();
        int randomid = rid();
        Item item = a[randomid];
        a[randomid] = a[N - 1];
        a[N - 1] = null;
        N--;
        // shrink array if necessary.
        if (N > 0 && N == a.length/4) {
            resize(a.length/2);
        }
        return item;
    }

    /**
     * @return a random item from the queue.
     */
    public Item sample() {
        checkEmpty();
        return a[rid()];
    }

    /**
     * @return an iterator over items in random order.
     */
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    /**
     * @throws NoSuchElementException if queue is empty.
     */
    private void checkEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
    }

    /**
     * @return a random index.
     */
    private int rid() {
        return StdRandom.uniform(N);
    }

    /**
     * Resizes the array in order to have certain capacity.
     * @param max the size of the array.
     * @return
     */
    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i % a.length];
        }
        a = temp;
    }


    /**
     * An Iterator that iterates through items in random order.
     */
    private class RandomizedIterator implements Iterator<Item> {

        /**
         * The current index in the array.
         */
        private int i = 0;

        /**
         * The random index array.
         */
        private int[] randidx;

        /**
         * Constructs a shuffled array from the one given.
         */
        public RandomizedIterator() {
            randidx = new int[N];
            for (int j = 0; j < N; j++) {
                randidx[j] = j;
            }
            StdRandom.shuffle(randidx);
        }

        /**
         * @see java.util.Iterator#hasNext()
         * @return true if the collection object has at least another item left.
         */
        public boolean hasNext()  {
            return i < N;
        }

        /**
         * Remove not supported.
         * @see java.util.Iterator#remove()
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * @return the next random item of the collection if any.
         * @see java.util.Iterator#next()
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = a[randidx[i]];
            i++;
            return item;
        }
    }
}
