package com.nighthawk.spring_portfolio.sorters;

import java.io.FileWriter;
import java.io.IOException;

public class CSVUtil {

    public static void saveDataToCSV(double[] data, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (double value : data) {
                fileWriter.write(value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
