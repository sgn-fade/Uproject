
import static java.lang.Math.*;

public class Main {
    public static void main(String[] args){}
    public static double mathFunction(double x){
        return exp(-pow(x, 2)) * sin(x);
    }
    public static double mathFunction(double x, double parameter){
        return exp(-parameter * pow(x, 2)) * sin(x);
    }
}
