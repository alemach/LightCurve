package ale.mach.lightcurve.service;

import ale.mach.lightcurve.model.TestCurveDTO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PlotSeriesToFile {

    public static void plotToFile(TestCurveDTO testCurveDTO) {
        final XYSeries series = new XYSeries("KIC " + String.valueOf(testCurveDTO.getKeplerId()));

        for (int i = 0; i < testCurveDTO.getFlux().length; i++) {

            series.add(testCurveDTO.getTime()[i], testCurveDTO.getFlux()[i]);
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "KIC " + String.valueOf(testCurveDTO.getKeplerId()),
                "TIME / BJD - 2454833",
                "PDCSAP_FLUX / e-/s",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        File XYChart = new File("src/main/webapp/resources/LightCurveChart.jpeg");
        try {
            ChartUtilities.saveChartAsJPEG(XYChart, xylineChart, 1920, 1080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
