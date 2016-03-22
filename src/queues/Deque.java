import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private final static int BASE_SIZE = 10;
    private Item[] data;
    private int head;
    private int tail;
    private int size = 0;

    // construct an empty deque
    public Deque() {
        data = (Item[]) new Object[BASE_SIZE];
        head = 0;
        tail = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size <= 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (size() >= data.length)
            resize(data.length * 2);

        data[head--] = item;
        if (head < 0)
            head += data.length;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();

        if (size() >= data.length)
            resize(data.length * 2);
        if (++tail >= data.length)
            tail %= data.length;
        data[tail] = item;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size() <= data.length / 4)
            resize(data.length / 2);
        data[head] = null;
        if (++head >= data.length)
            head %= data.length;
        size--;
        return data[head];
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size() <= data.length / 4)
            resize(data.length / 2);
        size--;
        Item removed = data[tail--];
        if (tail < 0)
            tail += data.length;

        return removed;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Item> {

        private int index = head + 1;
        private int currentSize = size;

        @Override
        public boolean hasNext() {
            return index <= currentSize + head;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item next = data[index++ % data.length];
            return next;
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
        int index = 1;
        for (Item item : this) {
            if (index == resizedData.length) break;
            resizedData[index++] = item;
        }
        head = 0;
        tail = index - 1;
        data = resizedData;
    }

//    public void print() {
//        StdOut.println("data array size : " + data.length);
//        StdOut.println("head : " + head);
//        StdOut.println("tail : " + tail);
//        StdOut.println("size : " + size);
//        for (Item item : this) {
//            StdOut.print(item + ", ");
//        }
//        StdOut.println();
//    }
}