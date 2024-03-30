import java.io.IOException;
import java.util.TreeSet;

public class TreeSetInterpolation extends Interpolator{
    private TreeSet<PointData> data = null;

    public TreeSetInterpolation() {
        data = new TreeSet<PointData>();
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
    public static void main(String[] args) {
        TreeSetInterpolation fun = new TreeSetInterpolation();
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
            fun.addPoint(new PointData(x, Math.sin(x)));
        }
        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));
        fun.sort();
        System.out.println("Відсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));
        System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення x: " +
                fun.getPoint(fun.numPoints()-1).getX());
        x = 0.5*(fun.getPoint(0).getX() +
                fun.getPoint(fun.numPoints()-1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " +
                fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютна помилка = " +
                Math.abs(fun.evalf(x)-Math.sin(x)));
        try {
            FileLoader.writeToFile("TreeSet.csv", fun);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
