import java.util.Set;
import java.util.TreeSet;


public class KdTree {

    private Set<Point2D> points = new TreeSet<>();

    // construct an empty set of points
    public KdTree()
    { }

    // is the set empty?
    public boolean isEmpty()
    {
        return points.isEmpty();
    }

    // number of points in the set
    public int size()
    {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p)
    {
        if (p == null) throw new NullPointerException();

        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p)
    {
        if (p == null) throw new NullPointerException();

        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw()
    { }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect)
    {
        if (rect == null) throw new NullPointerException();

        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p)
    {
        if (p == null) throw new NullPointerException();

        return null;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args)
    { }
}
