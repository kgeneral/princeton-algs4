import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class UnitTests {
    // unit testing
    public static void main(String[] args) {

        int N = StdRandom.uniform(100, 300);
        StdOut.println("N : " + N);

//        Deque<Integer> deque = new Deque<>();
//        IntStream.range(0, N).sequential().forEach(i -> {
//            int val = StdRandom.uniform(1, N);
//            int operationCase = val % 4;
//            try {
//                if (operationCase == 0)
//                    deque.addFirst(val);
//                if (operationCase == 1)
//                    deque.addLast(val);
//                if (operationCase == 2)
//                    deque.removeFirst();
//                if (operationCase == 3)
//                    deque.removeLast();
//            } catch (NoSuchElementException e) {
//
//            } catch (ArrayIndexOutOfBoundsException e) {
//                StdOut.println("operationCase : " + operationCase);
//                StdOut.println("ArrayIndexOutOfBoundsException");
//                //deque.print();
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        });
        //deque.print();

        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        IntStream.range(0, N).sequential().forEach(i -> {
            int val = StdRandom.uniform(1, N);
            int operationCase = val % 10;
            try {
                int removed = -1;
                if (operationCase >= 5)
                    randomizedQueue.enqueue(val);
                else
                    removed = randomizedQueue.dequeue();

                StdOut.println((operationCase >= 5) ? "enqueue " + val : "dequeue " + removed);

                //print(randomizedQueue);
                for (Integer item : randomizedQueue) {
                    StdOut.print(item + ", ");
                }
                StdOut.println();

            } catch (NoSuchElementException e) {
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });
    }

    private static <T> void print(Iterable<T> iterable) {
        for (T item : iterable) {
            StdOut.print(item.toString() + ", ");
        }
        StdOut.println();
    }


}
