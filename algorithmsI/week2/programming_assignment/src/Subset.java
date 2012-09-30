/**
 *  @author        Gerasimos Athanasopoulos.
 *  Login:         ath.ger@gmail.com
 *  Written:       8/30/2012
 *  Last updated:  8/30/2012
 *
 *  Compilation:   javac Subset.java
 *  Execution:     java Subset.java
 *
 *  Description:   A client that random prints out from stdin.
 */


public class Subset {

    /**
     * The test client.
     */
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RandomizedQueue<String> randq = new RandomizedQueue<String>();
//        System.setIn(new java.io.StringBufferInputStream("it was the best of times it was the worst of times"));
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randq.enqueue(item);
        }
        for (int i = 0; i < N; i++) {
            StdOut.println(randq.dequeue());
        }
    }

}
