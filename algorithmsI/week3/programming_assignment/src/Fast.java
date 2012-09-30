import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/*************************************************************************
 * Name: Gerasimos Athanasopoulos
 * Email: ath.ger@gmail.com
 *
 * Compilation:  javac Fast.java
 * Execution: java Fast input.txt
 *
 * Description: Fast algo for finding collinear points using sort.
 *
 *************************************************************************/


public class Fast {

    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.004);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] pts = new Point[N];
        ArrayList<Point> coll = new ArrayList<Point>();
        ArrayList<Point> prevcoll = new ArrayList<Point>();

        for (int i = 0; i < N; i++) {
            pts[i] = new Point(in.readInt(), in.readInt());
            pts[i].draw();
        }

        // iterate through every point in the array
        for (int i = 0; i < N; i++) {
            // Get the origin
            Point origin = pts[i];
            // Sort the remaining array
            Arrays.sort(pts, i + 1, N, origin.SLOPE_ORDER);

            int step;
            for (int j = i + 1; j < N; j += step) {
                // Initialize some vars
                for (Point point : coll) { prevcoll.add(point); }
                coll.clear();
                coll.add(origin);
                coll.add(pts[j]);
                step = 1;
                // Push into the coll array collinear points
                while (j + step < N &&
                        origin.SLOPE_ORDER.compare(pts[j], pts[j + step]) == 0) {
                    coll.add(pts[j + step]);
                    step++;
                }
                coll.removeAll(prevcoll);
                // If found more than 3 collinear draw the line
                // connecting the smallest to the largest
                if (coll.size() > 3) {
                    printColl(coll);
                }
            }
        }
    }

    private static void printColl(ArrayList<Point> coll1) {
        Collections.sort(coll1);
        coll1.get(0).drawTo(coll1.get(coll1.size() - 1));
        String output = "";
        for (int k = 0; k < coll1.size() - 1; k++) {
            output += coll1.get(k).toString() + " -> ";
        }
        StdOut.print(output + coll1.get(coll1.size() - 1) + "\n");
    }
}
