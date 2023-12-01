package com.nighthawk.spring_portfolio.fibo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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

    interface FibonacciAlgorithm {
        long calculateFibonacci(int n);
    }