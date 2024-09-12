package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.Range;

public class ChartExportTest {

    @Test
    public void testChartExport() throws Exception {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category A", 1.0);
        dataset.setValue("Category B", 2.0);

        JFreeChart chart = ChartFactory.createPieChart("Export Test", dataset, true, true, false);
        File outputFile = new File("output.png");
        ChartUtils.saveChartAsPNG(outputFile, chart, 400, 300);

        assertTrue(outputFile.exists(), "Exported chart file should exist.");
    }
}
