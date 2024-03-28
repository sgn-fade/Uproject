import java.io.*;
import java.util.StringTokenizer;

public abstract class FileLoader {
    public static void readFromFile(String fileName, ListInterpolation Int) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String s = in.readLine();
        Int.clear();
        while ((s = in.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            Int.addPoint(new Point2D(x, y));
        }
        in.close();
    }
    public static void writeToFile(String fileName, ListInterpolation Int) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        out.printf("%9s%25s\n", "x", "y");
        for (int i = 0; i < Int.numPoints(); i++) {
            out.println(Int.getPoint(i).getX() + "\t" + Int.getPoint(i).getY());
        }
        out.close();
    }
}
