package com.nighthawk.spring_portfolio.fibo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

public class FibonacciVisualizationWithGraph {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fibonacci Algorithm Efficiency Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Create chart panel
            JPanel chartPanel = new JPanel();
            frame.add(chartPanel, BorderLayout.CENTER);

            int numTerms = getUserInput(); // add JS API call

            FibonacciAlgorithm recursiveFibonacci = new RecursiveFibonacci();
            FibonacciAlgorithm iterativeFibonacci = new IterativeFibonacci();
            FibonacciAlgorithm memoizationFibonacci = new MemoizationFibonacci();

            XYSeries seriesRecursive = visualizeAlgorithmEfficiency(recursiveFibonacci, numTerms);
            XYSeries seriesIterative = visualizeAlgorithmEfficiency(iterativeFibonacci, numTerms);
            XYSeries seriesMemoization = visualizeAlgorithmEfficiency(memoizationFibonacci, numTerms);

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(seriesRecursive);
            dataset.addSeries(seriesIterative);
            dataset.addSeries(seriesMemoization);

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

            ChartPanel chartPanelComponent = new ChartPanel(chart);
            chartPanelComponent.setPreferredSize(new Dimension(800, 600));

            chartPanel.removeAll();
            chartPanel.add(chartPanelComponent);

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            byte[] chartImageBytes = generateChartImageBytes(chart);

            // Send chartImageBytes to the frontend (e.g., using a web framework)

            // Optionally, save the chart as an image file
            saveChartAsImage(chart, "chart.png");
        });
    }

    private static int getUserInput() {
        // Simulating the JS API call for user input
        String userInput = JOptionPane.showInputDialog("Enter the number of terms in the Fibonacci sequence:");
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Using default value.");
            return 10; // Default value
        }
    }

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

    private static void saveChartAsImage(JFreeChart chart, String filename) {
        try {
            ChartUtilities.saveChartAsPNG(new java.io.File(filename), chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface FibonacciAlgorithm {
        long calculateFibonacci(int n);
    }
    static class RecursiveFibonacci implements FibonacciAlgorithm {
        @Override
        public long calculateFibonacci(int n) {
            if (n <= 1) {
                return n;
            }
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }

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