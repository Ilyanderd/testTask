package com.ilyanders;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private final String outputDir;
    private final String prefix;
    private final boolean append;

    public FileProcessor(String outputDir, String prefix, boolean append) {
        this.outputDir = outputDir;
        this.prefix = prefix;
        this.append = append;
    }

    public void processFile(String inputFile, Statistics statistics) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String line;
        List<Long> integers = new ArrayList<>();
        List<Double> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            try {
                integers.add(Long.parseLong(line));
            } catch (NumberFormatException e1) {
                try {
                    floats.add(Double.parseDouble(line));
                } catch (NumberFormatException e2) {
                    strings.add(line);
                }
            }
        }

        reader.close();

        statistics.setIntegers(integers);
        statistics.setFloats(floats);
        statistics.setStrings(strings);
    }

    public <T> void writeToFile(List<T> data, String type) throws IOException {
        if (data.isEmpty()) {
            return;
        }

        String fileName = (outputDir.isEmpty() ? "" : outputDir + File.separator) + prefix + type;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append));

        for (T item : data) {
            writer.write(item.toString());
            writer.newLine();
        }

        writer.close();
    }
}