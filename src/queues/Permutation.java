import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        Integer num = Integer.parseInt(args[0]);      // input file

        while(!StdIn.isEmpty()) {
            //String str = in.readString();
            randomizedQueue.enqueue(StdIn.readString());
            
        }

        for (int i=0;i<num;i++)
            StdOut.println(randomizedQueue.dequeue());

    }
}
