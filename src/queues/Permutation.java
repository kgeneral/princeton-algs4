import edu.princeton.cs.algs4.In;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        Integer num = Integer.parseInt(args[0]);      // input file
        In in = new In();      // input file

        while(!in.isEmpty()) {
            String str = in.readString();
            randomizedQueue.enqueue(str);
        }

        for (int i=0;i<num;i++)
            System.out.println(randomizedQueue.dequeue());

    }
}
