package project2.cs6591;

import java.util.Iterator;

/**
 * This class was modified from "Algorithm, 4th edition"
 * by Robert Sedgewick and Kevin Wayne, Pg155.
 */

public class AdjacencyList<Item> implements Iterable<Item> {
    private Node first;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public void remove() {}

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
}
