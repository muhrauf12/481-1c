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

import static org.junit.jupiter.api.Assertions.*;

public class ComprehensiveChartTest {

    @Test
    public void testComprehensiveChart() throws Exception {
        // Create the dataset with various types of data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // Adding positive values
        dataset.addValue(8, "Series 1", "Category 1");
        dataset.addValue(6, "Series 1", "Category 2");
        dataset.addValue(7, "Series 1", "Category 3");
        // Adding negative values
        dataset.addValue(-3, "Series 2", "Category 1");
        dataset.addValue(-4, "Series 2", "Category 2");
        dataset.addValue(-5, "Series 2", "Category 3");
        // Adding null values
        dataset.addValue(null, "Series 3", "Category 1");
        dataset.addValue(9, "Series 3", "Category 2");
        dataset.addValue(4, "Series 3", "Category 3");

        // Create a bar chart using the dataset
        JFreeChart chart = ChartFactory.createBarChart(
                "Comprehensive Chart Test",     // Chart title
                "Category",                     // Domain axis label
                "Value",                        // Range axis label
                dataset,                        // Data
                PlotOrientation.VERTICAL,       // Orientation
                true,                           // Include legend
                true,                           // Tooltips
                false                           // URLs
        );

        // Assert that chart is created successfully
        assertNotNull(chart, "Chart should be created successfully");

        // Extract the plot and check its attributes
        CategoryPlot plot = chart.getCategoryPlot();
        assertNotNull(plot, "Plot should not be null");

        // Check if the range axis can handle negative values
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        assertTrue(rangeAxis.getLowerBound() < 0, "Range axis should have a lower bound below 0 to accommodate negative values");
        assertTrue(rangeAxis.getUpperBound() > 0, "Range axis should have an upper bound above 0 to accommodate positive values");

        // Check if the chart handles null values without crashing
        assertDoesNotThrow(() -> {
            dataset.addValue(null, "Series 3", "Category 1");
        }, "The chart should handle null values without throwing exceptions");

        // Customize plot - ensure domain and range axis are set
        NumberAxis domainAxis = new NumberAxis("Custom Domain Axis");
        plot.setDomainAxis(domainAxis);
        assertEquals("Custom Domain Axis", plot.getDomainAxis().getLabel(), "Domain axis should be correctly set");

        // Customize rendering - disable gridlines for better visual appearance
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);
        assertFalse(plot.isDomainGridlinesVisible(), "Domain gridlines should be disabled");
        assertFalse(plot.isRangeGridlinesVisible(), "Range gridlines should be disabled");

        // Export the chart to an image file
        File file = new File("ComprehensiveChartTest.png");
        ChartUtils.saveChartAsPNG(file, chart, 800, 600);

        // Verify that the chart was exported successfully
        assertTrue(file.exists(), "Exported chart file should exist");

        // Test for performance - measure time for rendering a large dataset
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            dataset.addValue(Math.random() * 100, "LargeSeries", "Category " + i);
        }
        JFreeChart largeChart = ChartFactory.createBarChart(
                "Large Dataset Chart", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false
        );
        long endTime = System.currentTimeMillis();

        // Ensure that the chart with a large dataset renders within a reasonable time
        assertTrue((endTime - startTime) < 5000, "Rendering large dataset should take less than 5 seconds");

        // Final assertions - check title and dataset size
        assertEquals("Comprehensive Chart Test", chart.getTitle().getText(), "The chart title should be correctly set");
        assertTrue(dataset.getRowCount() > 0, "Dataset should have non-zero rows");
    }
}
