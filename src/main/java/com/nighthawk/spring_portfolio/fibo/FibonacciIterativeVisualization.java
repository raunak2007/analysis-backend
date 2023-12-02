package com.nighthawk.spring_portfolio.fibo;

public class FibonacciIterativeVisualization {

    public static void main(String[] args) {
        // Get user input for the number of terms
        int n = getUserInput();

        // Generate Fibonacci terms using iterative algorithm and measure time
        double[] xData = new double[n];
        double[] yData = new double[n];

        for (int i = 1; i <= n; i++) {
            long startTime = System.nanoTime();
            int fibonacciTerm = fibonacciIterative(i);
            long endTime = System.nanoTime();
            long timeTaken = endTime - startTime;

            // Store data for visualization
            xData[i - 1] = i;
            yData[i - 1] = timeTaken;
        }

        // Create and display the chart
        FiboVisualizationUtil.displayChart(xData, yData, "Fibonacci Iterative");
    }

    private static int getUserInput() {
        // testing fixed value of 10
        return 10;
    }

    private static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }

        int fib = 1;
        int prev = 1;

        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib += prev;
            prev = temp;
        }

        return fib;
    }
}
