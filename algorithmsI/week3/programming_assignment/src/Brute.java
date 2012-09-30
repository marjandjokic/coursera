import java.util.Arrays;

/*************************************************************************
 * Name: Gerasimos Athanasopoulos
 * Email: ath.ger@gmail.com
 *
 * Compilation:  javac Brute.java
 * Execution: java Brute input.txt
 *
 * Description: Brute force for collinear points
 *
 *************************************************************************/


public class Brute {

    public static void main(String[] args) {
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] pts = new Point[N];

        for (int i = 0; i < N; i++) {
            pts[i] = new Point(in.readInt(), in.readInt());
            pts[i].draw();
        }

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.004);

        // iterate over all combinations (N choose 4)
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (!collinear(pts[i], pts[j], pts[k])) { continue; }
                    for (int l = k + 1; l < N; l++) {
                        if (collinear(pts[i], pts[j], pts[k], pts[l])) {
                            Point[] arr = new Point[]{ pts[i], pts[j], pts[k], pts[l] };
                            Arrays.sort(arr);
                            arr[0].drawTo(arr[3]);
                            StdOut.printf("%s -> %s -> %s -> %s\n", arr[0], arr[1], arr[2], arr[3]);
                        }
                    }
                }
            }
        }
    }


    private static boolean collinear(Point ref, Point... points) {
        double refslope = ref.slopeTo(points[0]);
        for (int i = 1; i < points.length; i++) {
            if (refslope != ref.slopeTo(points[i])) {
                return false;
            }
        }
        return true;
    }
}
