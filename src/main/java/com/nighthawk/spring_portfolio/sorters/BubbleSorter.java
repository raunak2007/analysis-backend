package com.nighthawk.spring_portfolio.sorters;

public class BubbleSorter extends Sorter {

    @Override
    public void sort(double[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // swap array[j] and array[j + 1]
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Sorter sorter = new BubbleSorter();
        sorter.benchmarkSort(10, 200, 10, 100);
    }
}
