import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NullValuesTest {

    @Test
    public void testNullValues() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", 20);
        dataset.setValue("Two", null);

        JFreeChart chart = ChartFactory.createPieChart("Null Values Test", dataset, true, true, false);
        
        assertFalse(chart == null, "Chart should handle null values without failing.");
    }
}
