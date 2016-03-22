import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.stream.IntStream;

public class UnitTests {
    // unit testing
    public static void main(String[] args) {

        int N = StdRandom.uniform(2000, 3000);
        StdOut.println("N : " + N);

        Deque<Integer> deque = new Deque<>();
        IntStream.range(0, N).forEach(i->{
            int val = StdRandom.uniform(1, N);
            int operationCase = val % 4;
            if(operationCase == 0)
                deque.addFirst(val);
            if(operationCase == 1)
                deque.addLast(val);
            if(operationCase == 2)
                deque.removeFirst();
            if(operationCase == 3)
                deque.removeLast();
        });
        deque.print();
    }

}
