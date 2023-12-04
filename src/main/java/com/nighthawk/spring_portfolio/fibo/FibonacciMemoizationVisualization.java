package com.nighthawk.spring_portfolio.fibo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FibonacciMemoizationVisualization {

    private static Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        // Get user input for the number of terms from the website
        int n = fetchNFromWebsite();

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

    private static int fetchNFromWebsite() {
        try {
            // Replace the URL with the actual URL of the website providing the value of n
            URL url = new URL("https://raunak2007.github.io/n-value-endpoint");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set up the connection
            connection.setRequestMethod("GET");

            // Read the response
            Scanner scanner = new Scanner(connection.getInputStream());
            if (scanner.hasNext()) {
                String nValue = scanner.next();
                return Integer.parseInt(nValue);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your actual code
        }

        // Default value if fetching fails
        return 10;
    }

    private static int fibonacciMemoization(int n) {
        if (n <= 1) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int fib = fibonacciMemoization(n - 1) + fibonacciMemoization(n - 2);
        memo.put(n, fib);

        return fib;
    }
}
