package com.mycompany.algoritmopractica5_2025;

import java.io.*;
import java.util.*;

public class CSVColumnar {
    private final LinkedHashMap<String, List<String>> columns = new LinkedHashMap<>();

    public CSVColumnar(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] headers = br.readLine().split(",", -1);
            for (String h : headers) {
                columns.put(h, new ArrayList<>());
            }
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                for (int i = 0; i < headers.length; i++) {
                    columns.get(headers[i]).add(i < parts.length ? parts[i] : "");
                }
            }
        }
    }

    public String[] getStrings(String columnName) {
        List<String> list = columns.get(columnName);
        return list != null ? list.toArray(new String[0]) : new String[0];
    }

    public double[] getDoubles(String columnName) {
        return columns.getOrDefault(columnName, Collections.emptyList())
                      .stream()
                      .mapToDouble(Double::parseDouble)
                      .toArray();
    }

    public int[] getInts(String columnName) {
        return columns.getOrDefault(columnName, Collections.emptyList())
                      .stream()
                      .mapToInt(s -> (int) Double.parseDouble(s))
                      .toArray();
    }

    public List<String> getHeaders() {
        // TODO Auto-generated method stub
        return new ArrayList<>(columns.keySet());

    }

    public List<String[]> getRows() {

        List<String[]> rows = new ArrayList<>();
        int numRows = columns.values().stream().mapToInt(List::size).max().orElse(0);
        for (int i = 0; i < numRows; i++) {
            String[] row = new String[columns.size()];
            int j = 0;
            for (String header : columns.keySet()) {
                List<String> colData = columns.get(header);
                row[j++] = i < colData.size() ? colData.get(i) : "";
            }
            rows.add(row);
        }
        return rows;
    }
}