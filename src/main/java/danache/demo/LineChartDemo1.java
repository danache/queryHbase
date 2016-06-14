
package danache.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration application showing how to create a line chart using
 * data from a {@link CategoryDataset}.
 */
public class LineChartDemo1 extends ApplicationFrame {

	/**
	 * Creates a new demo.
	 *
	 * @param title
	 *            the frame title.
	 * @throws ParseException 
	 */

	public LineChartDemo1(String title) throws ParseException {
		super(title);
		JPanel chartPanel = createDemoPanel(title,new HashMap<String, String> ());
		chartPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartPanel);
	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return The dataset.
	 */
	

	/**
	 * Creates a sample chart.
	 * 
	 * @param dataset
	 *            a dataset.
	 * 
	 * @return The chart.
	 * @throws ParseException
	 */
	private static JFreeChart createChart(String title, HashMap<String, String> datamap) throws ParseException {
		TimeSeries timeSeries = new TimeSeries("PM2.5数据显示", Hour.class);
		// 时间曲线数据集合
		TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		// 构造数据集合

		Collection<String> keyset = datamap.keySet();
		List<String> list = new ArrayList<String>(keyset);

		// 对key键值按字典升序排序
		Collections.sort(list);
		int tmp0 = 0;
		int tmp1 = 0;
		int dataValue = 0;;
		for (int i = 0; i < list.size(); i++) {
			
			String dateS = list.get(i);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date redate = sdf.parse(dateS);
			
			String value = datamap.get(list.get(i));
			if (value.equals( "-")){
				if((2 * tmp1 - tmp0) < 0){
					dataValue = tmp1;
				}
				else{
					dataValue = 2 * tmp1 - tmp0;
				}
			}
			else{
				dataValue =Integer.parseInt(value);
			}
			
			timeSeries.add(new Hour(redate.getHours(),(redate.getDate() ), (redate.getMonth() + 1), redate.getYear() + 1900
					), dataValue);

			tmp0 = tmp1;
			tmp1 = dataValue;
			
		}
		

		lineDataset.addSeries(timeSeries);
		JFreeChart chart = ChartFactory.createTimeSeriesChart("PM2.5趋势线", "时间", "PM2.5值/ug", lineDataset, true, true,
				true);
		// 设置子标题

		TextTitle subtitle = new TextTitle(title, new Font("黑体", Font.BOLD, 12));
		chart.addSubtitle(subtitle);
		// 设置主标题
		chart.setTitle(new TextTitle("PM2.5数据显示", new Font("隶书", Font.ITALIC, 15)));
		chart.setAntiAlias(true);

		XYPlot plot = (XYPlot) chart.getPlot();
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) plot.getRenderer();
		// 设置网格背景颜色
		plot.setBackgroundPaint(Color.white);
		// 设置网格竖线颜色
		plot.setDomainGridlinePaint(Color.pink);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.pink);
		// 设置曲线图与xy轴的距离
		plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 10D));
		// 设置曲线是否显示数据点
		xylineandshaperenderer.setBaseShapesVisible(true);
		// 设置曲线显示各数据点的值
		XYItemRenderer xyitem = plot.getRenderer();
		xyitem.setBaseItemLabelsVisible(true);
		xyitem.setBasePositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		// 下面三句是对设置折线图数据标示的关键代码
		xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 10));
		plot.setRenderer(xyitem);

		return chart;
	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 * 
	 * @return A panel.
	 * @throws ParseException 
	 */
	public static JPanel createDemoPanel(String title, HashMap<String, String> datamap) throws ParseException {
		JFreeChart chart = createChart(title, datamap);
		return new ChartPanel(chart);
	}

	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args
	 *            ignored.
	 */
	public static String generatePieChart(String title,  HashMap<String, String> datamap, HttpSession session, PrintWriter pw) {
		String filename = null;
		try {
			JFreeChart chart = createChart(title, datamap);
			chart.setBackgroundPaint(java.awt.Color.white);
			// 设置图形的背景色
			filename = ServletUtilities.saveChartAsPNG(chart, 800, 600, session);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

	public static void main(String[] args) throws ParseException {
		LineChartDemo1 demo = new LineChartDemo1("pm25");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

}
