import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeSeriesTest {

    @Test
    public void testTimeSeriesChart() {
        TimeSeries series = new TimeSeries("Hourly Data");
        series.add(new Day(1, 1, 2021), 50);
        series.add(new Day(2, 1, 2021), 80); 

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Time Series Test", 
                "Date", 
                "Value", 
                dataset, 
                true, 
                true, 
                false);

        assertEquals("Time Series Test", chart.getTitle().getText());
    }
}
