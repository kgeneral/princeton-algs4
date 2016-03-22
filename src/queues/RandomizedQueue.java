import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int BASE_SIZE = 10;
    private Item[] data;
    private int size;
    private int cursor;

    // construct an empty randomized queue
    public RandomizedQueue() {
        data = (Item[]) new Object[BASE_SIZE];
        size = 0;
        cursor = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size() <= 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        data[cursor++] = item;
        size++;
        if (cursor >= data.length)
            resize(data.length * 2);
    }

    private void resize(int targetSize) {
        // no more shrink under BASE_SIZE
        if (Math.max(data.length, targetSize) <= BASE_SIZE)
            return;

        Item[] resizedData = (Item[]) new Object[targetSize];
        int movableSize = Math.min(data.length, targetSize);
        int idx = 0;
        for (int i = 0; i < movableSize; i++) {
            if (data[i] == null)
                continue;

            resizedData[idx++] = data[i];
        }
        size = idx;
        data = resizedData;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        int randomIndex = getRandomIndex();
        Item removed = data[randomIndex];
        data[randomIndex] = null;
        size--;
        return removed;
    }

    private int getRandomIndex() {
        do {
            int randomIndex = StdRandom.uniform(data.length);
            if (data[randomIndex] != null)
                return randomIndex;
        } while (true);
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int randomIndex = getRandomIndex();
        return data[randomIndex];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Item> {

        private int cursor = 0;
        private final int currentSize = size;
        private boolean[] iterated = new boolean[data.length];

        @Override
        public boolean hasNext() {
            return cursor < currentSize;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            int randomIndex = getRandomIndex();
            Item next = data[randomIndex];
            cursor++;
            iterated[randomIndex] = true;
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private int getRandomIndex() {
            do {
                int randomIndex = StdRandom.uniform(data.length);
                if (iterated[randomIndex])
                    continue;
                if (data[randomIndex] == null)
                    continue;

                return randomIndex;
            } while (true);
        }
    }

    // unit testing
    public static void main(String[] args) {
    }

    //    private int[] shuffle(int size) {
//        int[] shuffle = new int[size];
//        for (int i = 0; i < size; i++)
//            shuffle[i] = i;
//
//        for (int i = 0; i < size - 1; i++) {
//            if (StdRandom.uniform(2) == 0)
//                swapIndex(i, i + 1);
//        }
//
//        return shuffle;
//    }
//
//    private void swapIndex(int left, int right) {
//        Item temp = data[left];
//        data[left] = data[right];
//        data[right] = temp;
//    }
}
