import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments;
    
    
    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        if(points == null) throw new NullPointerException("Null point vector");
        lineSegments = new ArrayList<LineSegment>();
        
        
    }
    
    public int numberOfSegments() { // the number of line segments
        return lineSegments.size();
    }
    
    public LineSegment[] segments() { // the line segments
        //convert arraylist to array
        return lineSegments.toArray(new LineSegment[0]);
    }
}