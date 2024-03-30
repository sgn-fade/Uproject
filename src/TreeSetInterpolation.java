import java.util.TreeSet;

public class TreeSetInterpolation extends Interpolator{
    private TreeSet<PointData> data = null;

    public TreeSetInterpolation() {
    }

    public TreeSetInterpolation(TreeSet<PointData> data) {
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
    public void addPoint(PointData pt) {
        data.add(pt);
    }

    @Override
    public PointData getPoint(int i) {
        int n = 0;
        for (PointData point : data) {
            if (n == i) {
                return point;
            }
            n++;
        }
        return null;
    }

    @Override
    public void setPoint(int i, PointData pt) {
        PointData p = getPoint(i);
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
