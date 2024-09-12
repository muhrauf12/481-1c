import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtils;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
