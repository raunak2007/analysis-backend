package com.nighthawk.spring_portfolio.sorters;

public abstract class Sorter {

    public abstract void sort(double[] array);

    public long timeSorting(double[] array) {
        long startTime = System.nanoTime();
        sort(array);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000; // Convert nanoseconds to milliseconds
    }
    

    public void benchmarkSort(int minStudents, int maxStudents, int stepSize, int runs) {
        for (int numStudents = minStudents; numStudents <= maxStudents; numStudents += stepSize) {
            long totalTime = 0;

            for (int run = 0; run < runs; run++) {
                double[][] xData = MockDataGenerator.generateXData(numStudents);
                double[] yData = MockDataGenerator.generateYData(xData);
                totalTime += timeSorting(yData);
            }

            double averageTime = totalTime / (double) runs;
            System.out.println("Number of Students: " + numStudents + ", Average Time: " + averageTime + " ms");
        }
    }
}
