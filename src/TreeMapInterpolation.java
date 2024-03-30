import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapInterpolation extends Interpolator{
    TreeMap<Double, Double> data = new TreeMap<>();
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
        data.put(pt.getX(), pt.getY());
    }

    @Override
    public Point2D getPoint(int i) {
        Map.Entry<Double, Double> e = null;
        Iterator<Map.Entry<Double, Double>> it = data.entrySet().iterator();
        while (0 <= i--) {e = it.next();}
        double x = 0;
        double y = 0;
        if (e != null) {
            x = e.getKey();
            y = e.getValue();
            return new Point2D(x, y);
        }
        return null;
    }

    @Override
    public void setPoint(int i, Point2D pt) {
        Point2D p = getPoint(i);
        data.remove(p.getX());
        addPoint(pt);
    }

    @Override
    public void removeLastPoint() {
        data.remove(data.lastKey());
    }

    @Override
    public void sort() {

    }
}