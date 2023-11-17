package com.nighthawk.spring_portfolio.sorters;

public class BinarySorter extends Sorter {

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int insertedPosition = findInsertionPoint(array, 0, i - 1, key);
            System.arraycopy(array, insertedPosition, array, insertedPosition + 1, i - insertedPosition);
            array[insertedPosition] = key;
        }
    }

    private int findInsertionPoint(int[] array, int start, int end, int key) {
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
}
