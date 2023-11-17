package com.nighthawk.spring_portfolio.sorters;

import java.util.Random;

public abstract class Sorter {

    private static final Random random = new Random();

    public abstract void sort(int[] array);

    public long timeSorting(int[] array) {
        long startTime = System.currentTimeMillis();
        sort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public void benchmarkSort(int minSize, int maxSize, int stepSize, int runs) {
        for (int size = minSize; size <= maxSize; size += stepSize) {
            long totalTime = 0;

            for (int run = 0; run < runs; run++) {
                int[] data = generateRandomData(size);
                totalTime += timeSorting(data);
            }

            double averageTime = totalTime / (double) runs;
            System.out.println("Array Size: " + size + ", Average Time: " + averageTime + " ms");
        }
    }

    private int[] generateRandomData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(1000); // Random integer between 0 and 999
        }
        return data;
    }
}
