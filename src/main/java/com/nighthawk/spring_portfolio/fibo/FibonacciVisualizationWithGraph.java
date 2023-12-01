import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FibonacciVisualizationWithGraph {

    // chart requests from frontend
    private static String handleChartRequest(Request request, Response response) {
        // get user input
        int numTerms = Integer.parseInt(request.queryParams("numTerms"));

        // Create instances of Fibonacci algorithms
        FibonacciAlgorithm recursiveFibonacci = new RecursiveFibonacci();
        FibonacciAlgorithm iterativeFibonacci = new IterativeFibonacci();
        FibonacciAlgorithm memoizationFibonacci = new MemoizationFibonacci();

        // Visualize algorithm efficiency and generate series for each algorithm
        XYSeries seriesRecursive = visualizeAlgorithmEfficiency(recursiveFibonacci, numTerms);
        XYSeries seriesIterative = visualizeAlgorithmEfficiency(iterativeFibonacci, numTerms);
        XYSeries seriesMemoization = visualizeAlgorithmEfficiency(memoizationFibonacci, numTerms);

        // Create dataset for JFreeChart
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesRecursive);
        dataset.addSeries(seriesIterative);
        dataset.addSeries(seriesMemoization);

        // Create JFreeChart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Fibonacci Algorithm Efficiency",
                "Number of Terms",
                "Time (nanoseconds)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Generate chart image bytes
        byte[] chartImageBytes = generateChartImageBytes(chart);

        // Set response content type and return the chart image bytes
        response.type("image/png");
        return new String(chartImageBytes);
    }

    // Helper method to visualize the efficiency of a Fibonacci algorithm and generate a series
    private static XYSeries visualizeAlgorithmEfficiency(FibonacciAlgorithm algorithm, int numTerms) {
        XYSeries series = new XYSeries(algorithm.getClass().getSimpleName());

        for (int i = 1; i <= numTerms; i++) {
            long startTime = System.nanoTime();
            algorithm.calculateFibonacci(i);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            series.add(i, elapsedTime);
        }

        return series;
    }

    // Helper method to generate chart image bytes from JFreeChart
    private static byte[] generateChartImageBytes(JFreeChart chart) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ChartUtilities.writeChartAsPNG(baos, chart, 800, 600);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    // Fibonacci algorithm that can be abstract, extended after 
    interface FibonacciAlgorithm {
        long calculateFibonacci(int n);
    }

    // recursive Fibonacci algorithm
    static class RecursiveFibonacci implements FibonacciAlgorithm {
        @Override
        public long calculateFibonacci(int n) {
            if (n <= 1) {
                return n;
            }
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }

    // iterative Fibonacci algorithm
    static class IterativeFibonacci implements FibonacciAlgorithm {
        @Override
        public long calculateFibonacci(int n) {
            if (n <= 1) {
                return n;
            }
            long a = 0, b = 1;
            for (int i = 2; i <= n; i++) {
                long temp = a + b;
                a = b;
                b = temp;
            }
            return b;
        }
    }

    // memoization Fibonacci algorithm
    static class MemoizationFibonacci implements FibonacciAlgorithm {
        private long[] memo = new long[100]; // Adjust the size based on your needs

        @Override
        public long calculateFibonacci(int n) {
            if (n <= 1) {
                return n;
            }
            if (memo[n] != 0) {
                return memo[n];
            }
            memo[n] = calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
            return memo[n];
        }
    }
}
