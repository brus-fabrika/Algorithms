import java.util.Arrays;


public class Fast {

    public static void main(String[] args) {

        if (args.length < 1) return;

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01); // make the points a bit larger

        // read in the input
        String filename = args[0];
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

        Arrays.sort(points);
        Point[] ps = Arrays.copyOf(points, points.length);

        for (int i = 0; i < N; ++i) {
            Point p = points[i];

            Arrays.sort(ps, p.SLOPE_ORDER);

            int c = 0;

            for (int j = 0; j < ps.length; j += c + 1) {

                double s = p.slopeTo(ps[j]);
                c = 0;

                while ((j + c + 1 < ps.length)
                        && s == p.slopeTo(ps[j + c + 1])) c++;

                if (c >= 2) {
                    Point[] p1 = new Point[c + 2];
                    System.arraycopy(ps, j, p1, 0, c+1);
                    p1[p1.length - 1] = p;

                    Arrays.sort(p1);
                    if (p == p1[0]) {
                        p1[0].drawTo(p1[p1.length - 1]);

                        StdOut.print(p1[0]);
                        for (int k = 1; k < p1.length; ++k) {
                            StdOut.print(" -> " + p1[k]);
                        }
                        StdOut.println();
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
