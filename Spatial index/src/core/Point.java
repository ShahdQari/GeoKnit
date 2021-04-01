/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author DELL
 */
public class Point implements Shape, Comparable<Point> {
    public int type;
    public double x;
    public double y;

    public Point() {
        this(0, 0);
    }

    public Point(double x, double y) {
        set(x, y);
    }

    /**
     * A copy constructor from any shape of type Point (or subclass of Point)
     *
     * @param s
     */
    public Point(Point s) {
        this.x = s.x;
        this.y = s.y;
    }
    
    public Point(int type){
        set(type);
    }
    public void set(int type){
        this.type = type;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Shape s) {
        Point pt2 = (Point) s;

        // Sort by id
        double difference = this.x - pt2.x;
        if (difference == 0) {
            difference = this.y - pt2.y;
        }
        if (difference == 0) {
            return 0;
        }
        return difference > 0 ? 1 : -1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Point r2 = (Point) obj;
        return this.x == r2.x && this.y == r2.y;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.x);
        result = (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.y);
        result = 31 * result + (int) (temp ^ temp >>> 32);
        return result;
    }

    public double distanceTo(Point s) {
        double dx = s.x - this.x;
        double dy = s.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public Point clone() {
        return new Point(this.x, this.y);
    }

    /**
     * Returns the minimal bounding rectangle of this point. This method returns
     * the smallest rectangle that contains this point. For consistency with
     * other methods such as {@link Rectangle#isIntersected(Shape)}, the
     * rectangle cannot have a zero width or height. Thus, we use the method
     * {@link Math#ulp(double)} to compute the smallest non-zero rectangle that
     * contains this point. In other words, for a point <code>p</code> the
     * following statement should return true.
     * <code>p.getMBR().isIntersected(p);</code>
     */
    @Override
    public Rectangle getMBR() {
        return new Rectangle(x, y, x + Math.ulp(x), y + Math.ulp(y));
    }

    @Override
    public double distanceTo(double px, double py) {
        double dx = x - px;
        double dy = y - py;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Shape getIntersection(Shape s) {
        return getMBR().getIntersection(s);
    }

    @Override
    public boolean isIntersected(Shape s) {
        return getMBR().isIntersected(s);
    }

    @Override
    public String toString() {
        return "Point: (" + x + " " + y + ")";
    }

    @Override
    public int compareTo(Point o) {
        if (x < o.x) {
            return -1;
        }
        if (x > o.x) {
            return 1;
        }
        if (y < o.y) {
            return -1;
        }
        if (y > o.y) {
            return 1;
        }
        return 0;
    }

}
