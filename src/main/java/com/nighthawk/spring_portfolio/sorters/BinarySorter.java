package com.nighthawk.spring_portfolio.sorters;

public class BinarySorter extends Sorter {

    @Override
    public void sort(double[] array) {
        for (int i = 1; i < array.length; i++) {
            double key = array[i];
            int insertedPosition = findInsertionPoint(array, 0, i - 1, key);
            System.arraycopy(array, insertedPosition, array, insertedPosition + 1, i - insertedPosition);
            array[insertedPosition] = key;
        }
    }

    private int findInsertionPoint(double[] array, int start, int end, double key) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
    public class Main {
        public static void main(String[] args) {
            Sorter sorter = new BinarySorter();
            sorter.benchmarkSort(1000, 10000, 1000, 100);
        }
    }
    
}
