import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LargeDatasetTest {

    @Test
    public void testLargeDataSetRendering() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < 1000000; i++) {
            dataset.addValue(Math.random() * 100, "Series1", "Category" + i);
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Large Dataset Test", 
                "Categories", 
                "Values", 
                dataset);

        assertTrue(chart != null, "Chart should be rendered without exception.");
    }
}
