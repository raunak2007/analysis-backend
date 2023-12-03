package com.nighthawk.spring_portfolio.fibo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;

public class FiboVisualizationUtil extends ApplicationFrame {

    public FiboVisualizationUtil(String title, double[] xData, double[] yData) {
        super(title);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                title,
                "Number of Terms",
                "Time Taken (ns)",
                createDataset(xData, yData)
        );

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        setContentPane(chartPanel);
    }

    private XYDataset createDataset(double[] xData, double[] yData) {
        XYSeries series = new XYSeries("Fibonacci Time");
        for (int i = 0; i < xData.length; i++) {
            series.add(xData[i], yData[i]);
        }

        XYDataset dataset = new XYSeriesCollection(series);
        return dataset;
    }

    public static void displayChart(double[] xData, double[] yData, String title) {
        SwingUtilities.invokeLater(() -> {
            FiboVisualizationUtil example = new FiboVisualizationUtil(title, xData, yData);
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
