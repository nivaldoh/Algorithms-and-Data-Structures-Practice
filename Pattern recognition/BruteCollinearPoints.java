import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments;
    
    
    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        if(points == null) throw new NullPointerException("Null point vector");
        lineSegments = new ArrayList<LineSegment>();
        int n = points.length;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    for(int l = k + 1; l < n; l++) {
                        if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])){
                            lineSegments.add(new LineSegment(points[i], points[l])); // this is wrong. The two endpoints need to be the most distant ones from each other in a segment
                            lineSegments.add(new LineSegment(points[i], points[j])); // temporary solution
                            lineSegments.add(new LineSegment(points[i], points[k])); // TODO: use Point.compareTo()
                            lineSegments.add(new LineSegment(points[j], points[k]));
                            lineSegments.add(new LineSegment(points[j], points[l]));
                            lineSegments.add(new LineSegment(points[k], points[l]));
                        }
                    }
                }
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