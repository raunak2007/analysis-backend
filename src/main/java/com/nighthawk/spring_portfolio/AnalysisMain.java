package com.nighthawk.spring_portfolio.analysis;

import com.nighthawk.spring_portfolio.fibo.*;
import com.nighthawk.spring_portfolio.sorters.*;

public class AnalysisMain {

    public static void main(String[] args) {
        // Perform Fibonacci analysis
        performFibonacciAnalysis();

        // Perform sorting algorithm analysis
        performSortingAlgorithmAnalysis();
    }

    private static void performFibonacciAnalysis() {
        int numTerms = getUserInput();

        // Concrete Classes for Fibonacci Algorithms
        FibonacciAlgorithm recursiveFibonacci = new RecursiveFibonacci();
        FibonacciAlgorithm iterativeFibonacci = new IterativeFibonacci();
        FibonacciAlgorithm dynamicProgrammingFibonacci = new DynamicProgrammingFibonacci();

        // Visualization with JFreeChart
        XYSeries seriesRecursive = visualizeAlgorithmEfficiency(recursiveFibonacci, numTerms);
        XYSeries seriesIterative = visualizeAlgorithmEfficiency(iterativeFibonacci, numTerms);
        XYSeries seriesDynamicProgramming = visualizeAlgorithmEfficiency(dynamicProgrammingFibonacci, numTerms);

        // Additional analysis or calculations if needed

        // You can add more analysis or calculations related to Fibonacci if needed
    }

    private static void performSortingAlgorithmAnalysis() {
        Sorter sorter = new BinarySorter();
        sorter.benchmarkSort(10, 200, 10, 100);

        // Additional analysis or calculations if needed

        // You can add more analysis or calculations related to sorting algorithms if needed
    }

    // Helper method to get user input for the number of terms in the Fibonacci sequence
    private static int getUserInput() {
        // You may want to replace this with more sophisticated user input handling
        return 10; // Default value for demonstration purposes
    }

    // Helper method to visualize the efficiency of a Fibonacci algorithm
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
}
