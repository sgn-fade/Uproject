import java.io.*;

public abstract class FileLoader {
    public static void readFromFile(String fileName, Interpolator Int) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = in.readLine();
        Int.clear();
        while ((line = in.readLine()) != null) {
            String[] data = line.split(";");
            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);
            Int.addPoint(new PointData(x, y));
        }
        in.close();
    }
    public static void writeToFile(String fileName, Interpolator Int) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        out.println("x"+ ";" + "y");
        for (int i = 0; i < Int.numPoints(); i++) {
            out.println(Int.getPoint(i).getX() + ";" + Int.getPoint(i).getY());
        }
        out.close();
    }
}
