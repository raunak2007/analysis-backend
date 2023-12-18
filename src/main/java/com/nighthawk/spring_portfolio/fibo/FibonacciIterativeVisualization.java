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
        return 100;
    }

        // Iterative implementation of the Fibonacci sequence
    private static int fibonacciIterative(int n) {
    // Base case: if n is 0 or 1, return n
        if (n <= 1) {
        return n;
        }

    // Initialize variables to store the current Fibonacci number (fib)
    // and the previous Fibonacci number (prev)
        int fib = 1;
        int prev = 1;

    // Loop from the third Fibonacci number up to the nth
        for (int i = 2; i < n; i++) {
        // Update fib by adding the previous Fibonacci number
        // Update prev to the previous value of fib
            int temp = fib;
            fib += prev;
            prev = temp;
    }

    // Return the nth Fibonacci number
    return fib;
}
}