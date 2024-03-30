public class PointData implements Comparable<PointData> {
    private double[] coords = null;

    public PointData(double x, double y) {
        this.coords = new double[2];
        setCoord(1, x); setCoord(2, y);
    }
    public PointData() {
        this(0, 0);
    }
    public double getX() {
        return getCoord(1);
    }
    public void setX(double x) {
        setCoord(1, x);
    }
    public double getY() {
        return getCoord(2);
    }
    public void setY(double y) {
        setCoord(2, y);

    }
    public void setCoord(int num, double x) {
        coords[num-1] = x;
    }
    public double getCoord(int num) {
        return coords[num-1];
    }

    @Override
    public int compareTo(PointData pt) {
        return Double.compare(getX(), pt.getX());
    }
    public String toString() {
        StringBuilder res = new StringBuilder("(");
        for (double x : coords) {
            res.append(x).append(", ");
        }
        return res.substring(0, res.length()-2) + ")";
    }
    public static void main(String[] args) {
        java.util.List<PointData> data = new java.util.ArrayList<PointData>();
        int num;
        double x;
        java.util.Scanner in = new java.util.Scanner(System.in);
        do {
            System.out.print("Кількість точок: ");
            num = in.nextInt();
        } while (num <= 0);
        for (int i = 0; i < num; i++)
        {
            x = 1.0 + (5.0 - 1.0)*Math.random();
            data.add(new PointData(x, Math.sin(x)));
        }
        System.out.println("Несортовані дані: ");
        for (PointData pt : data)
            System.out.println(pt);
        System.out.println("\nВідсортовані дані: ");
        java.util.Collections.sort(data);
        for (PointData pt : data)
            System.out.println("x = " + pt.getX() + "\ty = " + pt.getY());
    }
}