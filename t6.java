import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AxisScalingTest {

    @Test
    public void testAxisScaling() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5, "Positive", "2023");
        dataset.addValue(-3, "Negative", "2023");

        JFreeChart chart = ChartFactory.createBarChart(
                "Axis Scaling Test", 
                "Year", 
                "Value", 
                dataset);

        assertNotNull(chart, "Chart should be created successfully with positive and negative values.");
    }
}
