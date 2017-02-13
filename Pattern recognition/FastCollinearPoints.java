import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;
    private ArrayList<Point> p;
    
    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        if (points == null) throw new NullPointerException("Null point array");
        lineSegments = new ArrayList<LineSegment>();
        int n = points.length;
        
        // check the slope of each point to each other point, and add them to an array in order
        for(int i = 0; i < n; i++) {
            Point current = points[i];
            p = new ArrayList<Point>(n);
            p.set(0, current);
            for(int j = 1; j < n; j++){
                
                
            }
        }
        
    }
    
    public int numberOfSegments() { // the number of line segments
        return lineSegments.size();
    }
    
    public LineSegment[] segments() { // the line segments
        //convert arraylist to array
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
    
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}