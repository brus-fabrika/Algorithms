import java.util.Arrays;

public class Brute {

    public static void main(String[] args) {

        if (args.length < 1) return;

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01); // make the points a bit larger

        // read in the input
        String filename = args[0];
        // String filename = "grid4x4.txt";
        // String filename = "input6.txt";
        // String filename = "input400.txt";
        // String filename = "horizontal5.txt";
        // String filename = "vertical5.txt";
        In in = new In(filename);
        int N = in.readInt();

        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            points[i] = p;
        }

        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                for (int k = j + 1; k < N; ++k) {
                    for (int l = k + 1; l < N; ++l) {
                        double pq = points[i].slopeTo(points[j]);
                        double pr = points[i].slopeTo(points[k]);
                        double ps = points[i].slopeTo(points[l]);

                        boolean col = (pq == pr && pq == ps && pr == ps);

                        if (col) {
                            Point[] p1 = { points[i], points[j], points[k],
                                    points[l] };
                            Arrays.sort(p1);

                            StdOut.println(p1[0] + " -> " + p1[1] + " -> "
                                    + p1[2] + " -> " + p1[3]);
                            p1[0].drawTo(p1[3]);
                        }
                    }
                }
            }
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();

    }

}
