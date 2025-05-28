package com.mycompany.algoritmopractica5_2025;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestTimingCSV {

    public static void main(String[] args) {
        String csvPath = "C:\\Users\\trejo\\OneDrive\\Documentos\\NetBeansProjects\\Algoritmopractica5_2025\\src\\main\\java\\com\\mycompany\\algoritmopractica5_2025\\weatherHistory.csv";
        CSVColumnar csv;
        try {
            csv = new CSVColumnar(csvPath);
        } catch (IOException e) {
            System.err.println("Error leyendo CSV: " + e.getMessage());
            return;
        }

        WeatherData wd = new WeatherData(csv);

        // Columnas numéricas a probar
        Map<String, double[]> columns = new LinkedHashMap<>();
        columns.put("Temperature (C)",          wd.getTemperatures());
        columns.put("Apparent Temperature (C)", wd.getApparentTemps());
        columns.put("Humidity",                 wd.getHumidity());
        columns.put("Visibility (km)",          wd.getVisibility());
        columns.put("Cloud Cover",              wd.getCloudCover());
        columns.put("Pressure (millibars)",     wd.getPressure());

        // Algoritmos a comparar
        String[] algos = {
            "QuickSort",
            "SelectionSort",
            "ShellSort",
            "MergeSort",
            "Arrays.sort",
            "Arrays.parallelSort"
        };

        // Cabecera
        System.out.printf("%-25s", "Columna");
        for (String a : algos) {
            System.out.printf("%15s", a);
        }
        System.out.println();

        for (Map.Entry<String,double[]> e : columns.entrySet()) {
            String name = e.getKey();
            double[] base = e.getValue();

            System.out.printf("%-25s", name);

            // Convertir a Double[]
            Double[] boxed = Arrays.stream(base).boxed().toArray(Double[]::new);

            // QuickSort
            try {
                Double[] a1 = boxed.clone();
                long t0 = System.nanoTime();
                algoritmosSorts.quickSort(a1);
                long t1 = System.nanoTime();
                System.out.printf("%15d", (t1 - t0) / 1_000_000);
            } catch (Exception ex) {
                System.out.printf("%15s", "Error");
            }

            // SelectionSort
            try {
                Double[] a2 = boxed.clone();
                long t0 = System.nanoTime();
                algoritmosSorts.selectionSort(a2);
                long t1 = System.nanoTime();
                System.out.printf("%15d", (t1 - t0) / 1_000_000);
            } catch (Exception ex) {
                System.out.printf("%15s", "Error");
            }

            // ShellSort
            try {
                Double[] a3 = boxed.clone();
                long t0 = System.nanoTime();
                algoritmosSorts.shellSort(a3);
                long t1 = System.nanoTime();
                System.out.printf("%15d", (t1 - t0) / 1_000_000);
            } catch (Exception ex) {
                System.out.printf("%15s", "Error");
            }

            // MergeSort
            try {
                Double[] a4 = boxed.clone();
                long t0 = System.nanoTime();
                algoritmosSorts.mergeSort(a4);
                long t1 = System.nanoTime();
                System.out.printf("%15d", (t1 - t0) / 1_000_000);
            } catch (Exception ex) {
                System.out.printf("%15s", "Error");
            }

            // Arrays.sort
            try {
                Double[] a5 = boxed.clone();
                long t0 = System.nanoTime();
                Arrays.sort(a5);
                long t1 = System.nanoTime();
                System.out.printf("%15d", (t1 - t0) / 1_000_000);
            } catch (Exception ex) {
                System.out.printf("%15s", "Error");
            }

            // Arrays.parallelSort
            try {
                Double[] a6 = boxed.clone();
                long t0 = System.nanoTime();
                Arrays.parallelSort(a6);
                long t1 = System.nanoTime();
                System.out.printf("%15d", (t1 - t0) / 1_000_000);
            } catch (Exception ex) {
                System.out.printf("%15s", "Error");
            }

            System.out.println();
        }
    }
}
