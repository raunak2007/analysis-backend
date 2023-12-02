package com.nighthawk.spring_portfolio.fibo;

public abstract class FibonacciAlgorithm {

    public abstract long calculateFibonacci(int n);

    public long timeFibonacci(int n) {
        long startTime = System.nanoTime();
        calculateFibonacci(n);
        long endTime = System.nanoTime();
        return (endTime - startTime); // Convert nanoseconds to milliseconds
    }

    public long[][] benchmarkFibonacci(int minN, int maxN, int stepSize, int runs) {
        long[][] results = new long[(maxN - minN) / stepSize + 1][2];
        int resultIndex = 0;

        for (int n = minN; n <= maxN; n += stepSize) {
            long totalTime = 0;

            for (int run = 0; run < runs; run++) {
                totalTime += timeFibonacci(n);
            }

            long averageTime = totalTime / (long) runs;
            results[resultIndex][0] = n;
            results[resultIndex][1] = averageTime;
            resultIndex++;
        }

        return results;
    }
}
