package com.nighthawk.spring_portfolio.fibo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

import java.util.Scanner;


public class FibonacciVisualizationWithGraph {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Step 1: Create the main JFrame
            JFrame frame = new JFrame("Fibonacci Algorithm Efficiency Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Step 2: Create the chart panel using JFreeChart
            JPanel chartPanel = createChartPanel();
            frame.add(chartPanel, BorderLayout.CENTER);

            // Step 3: Set up the JFrame properties
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    /**
     * Create a JPanel with a line chart to visualize the efficiency of Fibonacci algorithms.
     *
     * @return ChartPanel containing the line chart.
     */
    private static JPanel createChartPanel() {
        // Step 1: Generate mock data using MockDataGenerator
        int numTerms = getUserInput();
        MockData mockData = generateFibonacciMockData(numTerms);

        // Step 2: Concrete Classes for Fibonacci Algorithms
        FibonacciAlgorithm recursiveFibonacci = new RecursiveFibonacci();
        FibonacciAlgorithm iterativeFibonacci = new IterativeFibonacci();
        FibonacciAlgorithm dynamicProgrammingFibonacci = new DynamicProgrammingFibonacci();

        // Step 3: Visualization with JFreeChart
        XYSeries seriesRecursive = visualizeAlgorithmEfficiency(recursiveFibonacci, mockData);
        XYSeries seriesIterative = visualizeAlgorithmEfficiency(iterativeFibonacci, mockData);
        XYSeries seriesDynamicProgramming = visualizeAlgorithmEfficiency(dynamicProgrammingFibonacci, mockData);

        // Step 4: Create dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesRecursive);
        dataset.addSeries(seriesIterative);
        dataset.addSeries(seriesDynamicProgramming);

        // Step 5: Create chart
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

        // Step 6: Create panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Step 7: Return the chart panel
        return chartPanel;
    }

    private static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of terms in the Fibonacci sequence: ");
        return scanner.nextInt();
    }


    private static MockData generateFibonacciMockData(int numTerms) {
        double[][] xData = new double[numTerms][4];
        double[] yData = new double[numTerms];

        for (int i = 0; i < numTerms; i++) {
            // Use Fibonacci sequence for xData
            xData[i][0] = fibonacci(i + 1);  // Commits
            xData[i][1] = fibonacci(i + 2);  // Pull Requests
            xData[i][2] = fibonacci(i + 3);  // Issues
            xData[i][3] = fibonacci(i + 4);  // Repos Contributed

            // Generate yData based on the Fibonacci terms
            yData[i] = fibonacci(i + 1) + fibonacci(i + 2) + fibonacci(i + 3) + fibonacci(i + 4);
        }

        return new MockData(xData, yData);
    }


    private static int fibonacci(int n) {
        if (n <= 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static XYSeries visualizeAlgorithmEfficiency(FibonacciAlgorithm algorithm, MockData mockData) {
        XYSeries series = new XYSeries(algorithm.getClass().getSimpleName());

        for (int i = 1; i <= mockData.getNumTerms(); i++) {
            long startTime = System.nanoTime();
            algorithm.calculateFibonacci(i);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            series.add(i, elapsedTime);
        }

        return series;
    }

    
    abstract static class FibonacciAlgorithm {
        abstract long calculateFibonacci(int n);
    }

   
    static class RecursiveFibonacci extends FibonacciAlgorithm {
        @Override
        long calculateFibonacci(int n) {
            if (n <= 1) {
                return n;
            }
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }

    
    static class IterativeFibonacci extends FibonacciAlgorithm {
        @Override
        long calculateFibonacci(int n) {
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

    /**
     * Concrete class representing a dynamic programming Fibonacci algorithm.
     */
    static class DynamicProgrammingFibonacci extends FibonacciAlgorithm {
        @Override
        long calculateFibonacci(int n) {
            if (n <= 1) {
                return n;
            }
            long[] dp = new long[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }

    /**
     * Class to encapsulate mock data.
     */
    private static class MockData {
        private final double[][] xData;
        private final double[] yData;

        public MockData(double[][] xData, double[] yData) {
            this.xData = xData;
            this.yData = yData;
        }

        public double[][] getXData() {
            return xData;
        }

        public double[] getYData() {
            return yData;
        }

        public int getNumTerms() {
            return xData.length;
        }
    }
}
