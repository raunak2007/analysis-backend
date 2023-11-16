package com.nighthawk.spring_portfolio.sorters;

public abstract class Sorter {

    /**
     * Sorts the given array.
     * 
     * @param array The array to be sorted.
     */
    public abstract void sort(int[] array);

    /**
     * Measures the time taken to sort an array.
     * 
     * @param array The array to be sorted.
     * @return The time taken to sort the array in milliseconds.
     */
    public long timeSorting(int[] array) {
        long startTime = System.currentTimeMillis();
        sort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
