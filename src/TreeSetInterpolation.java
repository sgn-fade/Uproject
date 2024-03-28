import java.util.List;
import java.util.TreeSet;

public class TreeSetInterpolation extends Interpolator{
    private TreeSet<Point2D> data = null;

    public TreeSetInterpolation() {
    }

    public TreeSetInterpolation(TreeSet<Point2D> data) {
        this.data = data;
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int numPoints() {
        return data.size();
    }

    @Override
    public void addPoint(Point2D pt) {
        data.add(pt);
    }

    @Override
    public Point2D getPoint(int i) {
        int n = 0;
        for (Point2D point : data) {
            if (n == i) {
                return point;
            }
            n++;
        }
        return null;
    }

    @Override
    public void setPoint(int i, Point2D pt) {
        Point2D p = getPoint(i);
        data.add(pt);
        addPoint(pt);
    }

    @Override
    public void removeLastPoint() {
        data.remove(data.last());
    }


    @Override
    public void sort() {

    }
}
