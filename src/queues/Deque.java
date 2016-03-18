import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static int BASE_SIZE = 10;
    private Item[] data;
    private int head;
    private int tail;

    // construct an empty deque
    public Deque() {
        data = (Item[]) new Object[BASE_SIZE];
        head = tail = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() <= 0;
    }

    // return the number of items on the deque
    public int size() {
        return tail - head;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();

        if (size() >= data.length)
            resize(data.length * 2);
        data[tail++] = item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return null;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size() <= data.length / 4)
            resize(data.length / 2);
        return data[--tail];
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            if(head == tail)
                throw new NoSuchElementException();
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int targetSize) {
        // no more shrink under BASE_SIZE
        if (Math.max(data.length, targetSize) <= BASE_SIZE)
            return;

        Item[] resizedData = (Item[]) new Object[targetSize];
        int movableLength = Math.min(data.length, targetSize);
        for (int i = head; i < movableLength + head; i++)
            resizedData[i - head] = data[i];

        tail -= head;
        head = 0;

        data = resizedData;
    }

    // unit testing
    public static void main(String[] args) {
        int N = StdRandom.uniform(100, 500);
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < N; i++) {
            int val = StdRandom.uniform(N);
            StdOut.print(val + " ");
            deque.addLast(val);
        }
        StdOut.println();
        StdOut.println("size : " + deque.size());

        for (int i = 0; i < N; i++)
            StdOut.print(deque.removeLast() + " ");

        StdOut.println();
        StdOut.println("size : " + deque.size());


    }
}