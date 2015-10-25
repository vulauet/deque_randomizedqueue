import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Subset {
    public static void main(String[] args) {
        int k;
        String s;
        RandomizedQueue<String> rq = new RandomizedQueue();
        k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            rq.enqueue(s);
        }
        for (int i = 0; i < k; i++)
            StdOut.println(rq.dequeue());
    }
}