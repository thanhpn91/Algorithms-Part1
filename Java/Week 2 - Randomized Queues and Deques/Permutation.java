import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] strings = StdIn.readAllStrings();
        
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        StdRandom.shuffle(strings);
        for (String s: strings) {
            StdOut.println(s);
            queue.enqueue(s);
        }
        
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}