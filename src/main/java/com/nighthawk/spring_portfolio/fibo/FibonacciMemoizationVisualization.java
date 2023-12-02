package com.nighthawk.spring_portfolio.fibo;

import java.util.HashMap;
import java.util.Map;

public class FibonacciMemoizationVisualization {

    private static Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        // Get user input for the number of terms
        int n = getUserInput();

        // Generate Fibonacci terms using memoization algorithm and measure time
        double[] xData = new double[n];
        double[] yData = new double[n];

        for (int i = 1; i <= n; i++) {
            long startTime = System.nanoTime();
            int fibonacciTerm = fibonacciMemoization(i);
            long endTime = System.nanoTime();
            long timeTaken = endTime - startTime;

            // Store data for visualization
            xData[i - 1] = i;
            yData[i - 1] = timeTaken;
        }

        // Create and display the chart
        FiboVisualizationUtil.displayChart(xData, yData, "Fibonacci Memoization");
    }

    private static int getUserInput() {
        // testing fixed value of 10
        return 10;
    }

    