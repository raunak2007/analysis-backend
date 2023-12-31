package com.nighthawk.spring_portfolio.fibo;

public class FibonacciRecursiveVisualization {

    public static void main(String[] args) {
        // Get user input for the number of terms
        int n = getUserInput();

        // Generate Fibonacci terms using recursive algorithm and measure time
        double[] xData = new double[n];
        double[] yData = new double[n];

        for (int i = 1; i <= n; i++) {
            long startTime = System.nanoTime();
            int fibonacciTerm = fibonacciRecursive(i);
            long endTime = System.nanoTime();
            long timeTaken = endTime - startTime;

            // Store data for visualization
            xData[i - 1] = i;
            yData[i - 1] = timeTaken;
        }

        // Create and display the chart
        FiboVisualizationUtil.displayChart(xData, yData, "Fibonacci Recursive");
    }

    private static int getUserInput() {
        // testing fixed value of 10
        return 100;
    }

    // Recursive implementation of the Fibonacci sequence
private static int fibonacciRecursive(int n) {
    // Base case: if n is 0 or 1, return n
    if (n <= 1) {
        return n;
    } else {
        // Recursive case: return the sum of the two previous Fibonacci numbers
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
}
}

