package zahrou;



import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.lang.ClassNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;


public class LineChartEx extends JFrame {

    public LineChartEx() {

        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public double multiply(int n ,double x ) {

    	double result = 1;
    	for(int i=0;i<n;i++){
    		result = result*x;
    	}
    	return result;
    }
    public long factorial(int k ) {
    	long result=1L;
    	if(k==0) {
    		return 1;
    	}else {
    		for(int i=1;i<=k;i++){
        		result =result*i;
        	//	System.out.println(result);
        	}
        	return result;
    	}
    	
    }
    public double sum(double t[]) {
    	double sum=0;
    	for(int i=0;i<t.length;i++){
    		sum +=t[i];
    	}
    	return sum;
    }
    public double lamda(double i, double pi) {
    	double result;
    	result = pi*multiply(2,i)*2;
    	return result; 
    }
    public double[] pK() {
    	double result[] = new double[20];
    	double x,mu,p0,pk[];
    	double pi=3.14;
    	mu=0.5;
    	for(int i=0;i<20;i++){
    		x=lamda(i,pi)/mu;
    		result[i]=multiply(i,x)/factorial(i);
    	
    	}
    	p0=1/sum(result);
    	pk=new double[20];
    	for(int i=0;i<20;i++){
    		pk[i]=result[i]*p0;
    		
    	}
    	return pk;
    }

    private XYDataset createDataset() {
    	double[] pk=new double[20];
    	/////
    	double result[] = new double[21];
    	double x,mu,mu2=15,p0,pki,lm;
    	double pi=3.14;
    	mu=20;
    	XYSeries series = new XYSeries("Mu=20");
    	XYSeries series2 = new XYSeries("Mu=15");
    	for(double i=0;i<=7;i+=0.5) {
    		p0=0;
    		lm=lamda(i,pi);
    		x=lm/mu;
    		for(int j=0;j<20;j++){		
        		result[j]=multiply(j,x)/factorial(j);
        	}
    		p0=1/sum(result);
    		pki=p0*(multiply(20,x)/factorial(20));
    		System.out.println("P"+ (i) +" == " +pki);
    		series.add(i,pki);
    	}
    	pki=0;
    	double result2[] = new double[21];
    	for(double i=0;i<=7;i+=0.5) {
    		p0=0;
    		lm=lamda(i,pi);
    		x=lm/mu2;
    		for(int j=0;j<21;j++){		
        		result2[j]=multiply(j,x)/factorial(j);
        	}
    		p0=1/sum(result2);
    		pki=p0*multiply(20,x)/factorial(20);
    		System.out.println("P"+ (i) +" == " +pki);
    		series2.add(i,pki);
    	}
    	      
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(series2);
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph",
                "Rayon",
                "PB",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Graph",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

        	LineChartEx ex = new LineChartEx();
            ex.setVisible(true);
        });
       
        
    }
}
