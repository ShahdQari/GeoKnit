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

public class Rectangle implements Shape{

    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public Rectangle() {
        this(0, 0, 0, 0);
    }

    /**
     * Constructs a new <code>Rectangle</code>, initialized to match the values
     * of the specified <code>Rectangle</code>.
     *
     * @param r the <code>Rectangle</code> from which to copy initial values to
     * a newly constructed <code>Rectangle</code>
     * @since 1.1
     */
    public Rectangle(Rectangle r) {
        this(r.x1, r.y1, r.x2, r.y2);
    }

    public Rectangle(double x1, double y1, double x2, double y2) {
        this.set(x1, y1, x2, y2);
    }

    public void set(Shape s) {
        if (s == null) {
            System.out.println("tozz");
            return;
        }
        Rectangle mbr = s.getMBR();
        set(mbr.x1, mbr.y1, mbr.x2, mbr.y2);
    }

    public void set(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Comparison is done by lexicographic ordering of attributes &lt; x1, y1,
     * x2, y2&gt;
     *
     * @return interger value
     */
      public double area() {
    return (x2 - x1) * (y2 - y1);
  }
    
    public int compareTo(Shape s) {
        Rectangle rect2 = (Rectangle) s;
        // Sort by x1 then y1
        if (this.x1 < rect2.x1) {
            return -1;
        }
        if (this.x1 > rect2.x1) {
            return 1;
        }
        if (this.y1 < rect2.y1) {
            return -1;
        }
        if (this.y1 > rect2.y1) {
            return 1;
        }

        // Sort by x2 then y2
        if (this.x2 < rect2.x2) {
            return -1;
        }
        if (this.x2 > rect2.x2) {
            return 1;
        }
        if (this.y2 < rect2.y2) {
            return -1;
        }
        if (this.y2 > rect2.y2) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Rectangle r2 = (Rectangle) obj;
        boolean result = this.x1 == r2.x1 && this.y1 == r2.y1
                && this.x2 == r2.x2 && this.y2 == r2.y2;
        return result;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.x1);
        result = (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.y1);
        result = 31 * result + (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.x2);
        result = 31 * result + (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.y2);
        result = 31 * result + (int) (temp ^ temp >>> 32);
        return result;
    }

    @Override
    public double distanceTo(double px, double py) {
        return this.getMaxDistanceTo(px, py);
    }

    /**
     * Maximum distance to the perimeter of the Rectangle
     *
     * @param px point
     * @param py point
     * @return distance in double
     */
    public double getMaxDistanceTo(double px, double py) {
        double dx = Math.max(px - this.x1, this.x2 - px);
        double dy = Math.max(py - this.y1, this.y2 - py);

        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getMinDistanceTo(double px, double py) {
        double dx, dy;
        if (px < this.x1) {
            dx = this.x1 - px;
        } else if (px < this.x2) {
            dx = 0;
        } else {
            dx = px - this.x2;
        }

        if (py < this.y1) {
            dy = this.y1 - py;
        } else if (py < this.y2) {
            dy = 0;
        } else {
            dy = py - this.y2;
        }

        if (dx > 0 && dy > 0) {
            return Math.sqrt(dx * dx + dy * dy);
        }
        return Math.max(dx, dy);
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }

    @Override
    public Rectangle getMBR() {
        return new Rectangle(this);
    }

    public boolean isIntersected(Shape s) {
        if (s instanceof Point) {
            Point pt = (Point) s;
            return pt.x >= x1 && pt.x < x2 && pt.y >= y1 && pt.y < y2;
        }
        Rectangle r = s.getMBR();
        if (r == null) {
            return false;
        }
        return (this.x2 > r.x1 && r.x2 > this.x1 && this.y2 > r.y1 && r.y2 > this.y1);
    }

    public Rectangle getIntersection(Shape s) {
        if (!s.isIntersected(this)) {
            return null;
        }
        Rectangle r = s.getMBR();
        double ix1 = Math.max(this.x1, r.x1);
        double ix2 = Math.min(this.x2, r.x2);
        double iy1 = Math.max(this.y1, r.y1);
        double iy2 = Math.min(this.y2, r.y2);
        return new Rectangle(ix1, iy1, ix2, iy2);
    }

    public boolean contains(Point p) {
        return contains(p.x, p.y);
    }
    
    public boolean Type(Point t) {
        if(!contains(t.x, t.y)){
            return false;
        }
            return true;
    }

    public boolean contains(double x, double y) {
        return x >= x1 && x < x2 && y >= y1 && y < y2;
    }

    public boolean contains(Rectangle r) {
        return contains(r.x1, r.y1, r.x2, r.y2);
    }

    public Rectangle union(final Shape s) {
        Rectangle r = s.getMBR();
        double ux1 = Math.min(x1, r.x1);
        double ux2 = Math.max(x2, r.x2);
        double uy1 = Math.min(y1, r.y1);
        double uy2 = Math.max(y2, r.y2);
        return new Rectangle(ux1, uy1, ux2, uy2);
    }

 public void expand(double x, double y) {
    if (x < this.x1)
      this.x1 = x;
    if (y < this.y1)
      this.y1 = y;
    if (x > this.x2)
      this.x2 = x;
    if (y > this.y2)
      this.y2 = y;
  }

  public void expand(final Shape s) {
    Rectangle r = s.getMBR();
    if (r.x1 < this.x1)
      this.x1 = r.x1;
    if (r.x2 > this.x2)
      this.x2 = r.x2;
    if (r.y1 < this.y1)
      this.y1 = r.y1;
    if (r.y2 > this.y2)
      this.y2 = r.y2;
  }

    public boolean contains(double rx1, double ry1, double rx2, double ry2) {
        return rx1 >= x1 && rx2 <= x2 && ry1 >= y1 && ry2 <= y2;
    }

    public Point getCenterPoint() {
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    /**
     * Compute the intersection of a line segment with the rectangle border. It
     * is assumed that p1 lies inside the rectangle while p2 is outside it.
     *
     * @param p1 point
     * @param p2 point
     * @return point intersect
     */
    public Point intersectLineSegment(Point p1, Point p2) {
        double vx = p2.x - p1.x;
        double vy = p2.y - p1.y;
        double a1 = ((vx >= 0 ? this.x2 : this.x1) - p1.x) / vx;
        double a2 = ((vy >= 0 ? this.y2 : this.y1) - p1.y) / vy;
        double a = Math.min(a1, a2);
        return new Point(p1.x + a * vx, p1.y + a * vy);
    }

    @Override
    public String toString() {
        return "Rectangle: (" + x1 + "," + y1 + ")-(" + x2 + "," + y2 + ")";
    }

    public boolean isValid() {
        return !Double.isNaN(x1);
    }

    public void invalidate() {
        this.x1 = Double.NaN;
    }

    public double getHeight() {
        return y2 - y1;
    }

    public double getWidth() {
        return x2 - x1;
    }

    /**
     * Compares two rectangles according to a lexicographic order of the
     * attributes (x1, y1, x2, y2)
     */
    public int compareTo(Rectangle r2) {
        if (this.x1 < r2.x1) {
            return -1;
        }
        if (this.x1 > r2.x1) {
            return 1;
        }
        if (this.y1 < r2.y1) {
            return -1;
        }
        if (this.y1 > r2.y1) {
            return 1;
        }

        if (this.x2 < r2.x2) {
            return -1;
        }
        if (this.x2 > r2.x2) {
            return 1;
        }
        if (this.y2 < r2.y2) {
            return -1;
        }
        if (this.y2 > r2.y2) {
            return 1;
        }

        return 0;
    }

    public Rectangle buffer(double dw, double dh) {
        return new Rectangle(this.x1 - dw, this.y1 - dh, this.x2 + dw, this.y2 + dh);
    }

    /**
     * Returns a new rectangle after translating with the given amount
     */
    public Rectangle translate(double dx, double dy) {
        return new Rectangle(this.x1 + dx, this.y1 + dy, this.x2 + dx, this.y2 + dy);
    }

    public String toWKT() {
        return String.format("\"POLYGON((%g %g, %g %g, %g %g, %g %g, %g %g))\"\n",
                x1, y1, x1, y2, x2, y2, x2, y1, x1, y1);
    }
    
    public String Apache() {
        return String.format("%g,%g,%g,%g"+"\n",
                x1, y1, x2, y2);
    }
}
