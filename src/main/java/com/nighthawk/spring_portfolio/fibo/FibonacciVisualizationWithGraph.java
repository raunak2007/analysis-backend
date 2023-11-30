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

