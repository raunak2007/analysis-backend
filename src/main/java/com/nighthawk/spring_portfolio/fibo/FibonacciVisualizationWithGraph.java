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
            // main JFrame
            JFrame frame = new JFrame("Fibonacci Algorithm Efficiency Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // JFreeChart chart panel
            JPanel chartPanel = createChartPanel();
            frame.add(chartPanel, BorderLayout.CENTER);

            // JFrame properties
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JPanel createChartPanel() {
        // getting the mock data using MockDataGenerator file from Vishnu
        int numTerms = getUserInput();
        MockData mockData = generateFibonacciMockData(numTerms);

        // fibo algorithm classes
        FibonacciAlgorithm recursiveFibonacci = new RecursiveFibonacci();
        FibonacciAlgorithm iterativeFibonacci = new IterativeFibonacci();
        FibonacciAlgorithm dynamicProgrammingFibonacci = new DynamicProgrammingFibonacci();

        // Step 3: Visualization with JFreeChart
        XYSeries seriesRecursive = visualizeAlgorithmEfficiency(recursiveFibonacci, mockData);
        XYSeries seriesIterative = visualizeAlgorithmEfficiency(iterativeFibonacci, mockData);
        XYSeries seriesDynamicProgramming = visualizeAlgorithmEfficiency(dynamicProgrammingFibonacci, mockData);

        // making the actual dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesRecursive);
        dataset.addSeries(seriesIterative);
        dataset.addSeries(seriesDynamicProgramming);

        // chart labels
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

        // creating the actual panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // returning the output
        return chartPanel;
    }

