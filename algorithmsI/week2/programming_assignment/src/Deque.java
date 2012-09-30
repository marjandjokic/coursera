import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  @author        Gerasimos Athanasopoulos.
 *  Login:         ath.ger@gmail.com
 *  Written:       8/30/2012
 *  Last updated:  8/30/2012
 *
 *  Compilation:   javac Deque.java
 *  Execution:     java Deque.java
 *
 *  Description:   Implementation of a double ended queue
 */


public class Deque<Item> implements Iterable<Item> {

    /**
     * The number of elements in the deque.
     */
    private int N;

    /**
     * The first node in the deque.
     */
    private Node fsnode;

    /**
     * The last item in the deque.
     */
    private Node lsnode;

    /**
     * A helper for a Doubly Linked List class.
     */
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    /**
     * Constructs an empty deque.
     * @return
     */
    public Deque() {
        N      = 0;
        fsnode = new Node();
        lsnode = new Node();
    }

    /**
     * Checks if the deque is empty.
     * @return true if the deque has no items.
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Calculates the number of items on the deque.
     * @return the size of the deque.
     */
    public int size() {
        return N;
    }

    /**
     * Inserts an item at the front of the deque.
     * @param item is the item to be inserted
     */
    public void addFirst(Item item) {
        if (isEmpty()) {
            insertFirst(item);
        } else {
            insertAfter(fsnode, item);
        }
    }

    /**
     * Inserts an item at the end of the deque.
     * @param item is the item to be inserted
     */
    public void addLast(Item item) {
        if (isEmpty()) {
            insertFirst(item);
        } else {
            insertBefore(lsnode, item);
        }
    }

    /**
     * Delete and return the item at the front of the deque.
     * @throws java.util.NoSuchElementException if deque is empty.
     * @return the item at the front of the deque.
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = fsnode.next.item;
        if (N == 1) { // remove references
            fsnode.next = null;
            lsnode.prev = null;
        } else {
            Node currentfirst = fsnode.next;
            currentfirst.prev = null;
            fsnode = currentfirst;
            fsnode.item = null;
        }
        N--;
        return item;
    }

    /**
     * Delete and return the item at the tail of the deque.
     * @throws java.util.NoSuchElementException if deque is empty.
     * @return the item at the tail of the deque.
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = lsnode.prev.item;
        if (N == 1) { // remove references
            fsnode.next = null;
            lsnode.prev = null;
        } else {
            Node currentlast = lsnode.prev;
            currentlast.next = null;
            lsnode = currentlast;
            lsnode.item = null;
        }
        N--;
        return item;
    }

    /**
     * It inserts an item after a specified node.
     * @param after is the node we are inserting after.
     * @param item is the item we are inserting.
     */
    private void insertAfter(Node after, Item item) {
        if (after == null || after.next == null || item == null) {
            throw new NullPointerException();
        }
        Node current = after.next;
        Node node = new Node();
        node.item = item;
        connect(after, node);
        connect(node, current);
        N++;
    }

    /**
     * It inserts an item before a specified node.
     * @param before is the node we are inserting before.
     * @param item is the item we are inserting.
     */
    private void insertBefore(Node before, Item item) {
        if (before == null || before.prev == null || item == null) {
            throw new NullPointerException();
        }
        Node current = before.prev;
        Node node = new Node();
        node.item = item;
        connect(node, before);
        connect(current, node);
        N++;
    }

    /**
     * @param item is the item we are inserting for the first time,
     * when the deque is empty.
     */
    private void insertFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.item = item;
        connect(fsnode, node);
        connect(node, lsnode);
        N++;
    }

    private void connect(Node node1, Node node2) {
        node1.next = node2;
        node2.prev = node1;
    }

    /**
     * Returns an iterator over items in order from front to end.
     * @return a first-to-last iterator.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }


    /**
     * An Iterator that iterates through items in first-to-last order.
     */
    private class ListIterator implements Iterator<Item> {
        private Node current = fsnode.next;

        /**
         * @see java.util.Iterator#hasNext()
         * @return true if the collection has at least another item left.
         */
        public boolean hasNext() {
            return current != null && lsnode != current;
        }

        /**
         * Remove not supported.
         * @see java.util.Iterator#remove()
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * @return the next item of the collection if any.
         * @throws java.util.NoSuchElementException if no next item.
         * @see java.util.Iterator#next()
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
