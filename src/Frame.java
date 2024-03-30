import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Frame extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldA;
    private JTextField textFieldF;
    private JTextField Start;
    private JTextField Stop;
    private JTextField Step;
    private XYSeries funSeries = new XYSeries("Function");
    private XYSeries derSeries = new XYSeries("Derivative");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame frame = new Frame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the frame.
     */
    public Frame() {
        setResizable(false);
        setTitle("fFreeChart Test Plot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 450, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JPanel panelButtons = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
        flowLayout.setHgap(15);
        contentPane.add(panelButtons, BorderLayout.SOUTH);
        JButton btnNewButtonPlot = new JButton("Plot");
        btnNewButtonPlot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                funSeries.clear();
                derSeries.clear();
                Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
                Variable var = new Variable("x");
                Variable par = new Variable("a");
                parser.add(var);
                parser.add(par);
                String funStr = String.valueOf(textFieldF.getText());
                Expression fun = parser.parse(funStr);
                Expression der = fun.derivative(var);
                par.setVal(1.0);
                parser.remove("a");

                double start = -9.0;
                double stop = 9.0;
                double step = 0.01;
                double a = 0;
                a = Double.parseDouble(textFieldA.getText());
                par.setVal(a);

                for (double x = start; x < stop; x += step) {
                    var.setVal(x);
                    funSeries.add(x, fun.getVal());
                    derSeries.add(x, der.getVal());
                }
            }
        });
        panelButtons.add(btnNewButtonPlot);
        JButton btnNewButtonExit = new JButton("Exit");
        btnNewButtonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panelButtons.add(btnNewButtonExit);
        JPanel panelData = new JPanel();
        contentPane.add(panelData, BorderLayout.NORTH);

        JLabel FunLabel = new JLabel("F(x):");
        panelData.add(FunLabel);
        textFieldF = new JTextField();
        textFieldF.setText("sin(a*x) / x");
        panelData.add(textFieldF);
        JLabel lblNewLabel = new JLabel("a:");
        panelData.add(lblNewLabel);
        textFieldA = new JTextField();
        textFieldA.setText("1.0");
        panelData.add(textFieldA);

        textFieldA.setColumns(10);
        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        contentPane.add(chartPanel, BorderLayout.CENTER);
    }
    private double f(double a, double x) {
        return Math.sin(a * x) / x;
    }
    private JFreeChart createChart() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(derSeries);
        dataset.addSeries(funSeries);
        JFreeChart chart = ChartFactory.createXYLineChart("",
// chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL, true, // include legend
                true, // tooltips
                false // urls
        );
// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        return chart;
    }
}