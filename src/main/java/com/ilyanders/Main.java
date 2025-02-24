package com.ilyanders;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption("o", "output-dir", true, "Путь к директории для выходных файлов");
        options.addOption("p", "prefix", true, "Префикс для имен файлов");
        options.addOption("s", "short-statistics", false, "Краткая статистика");
        options.addOption("f", "full-statistics", false, "Полная статистика");
        options.addOption("a", "append", false, "Режим добавления данных");

        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("Ошибка в аргументах командной строки.");
            return;
        }

        boolean shortStats = cmd.hasOption("s");
        boolean fullStats = cmd.hasOption("f");
        boolean append = cmd.hasOption("a");
        String outputDir = cmd.getOptionValue("o", "");
        String prefix = cmd.getOptionValue("p", "");

        List<String> inputFiles = Arrays.asList(cmd.getArgs());

        if (inputFiles.isEmpty()) {
            System.out.println("Не указаны входные файлы.");
            return;
        }

        FileProcessor processor = new FileProcessor(outputDir, prefix, append);
        Statistics statistics = new Statistics(shortStats, fullStats);

        for (String inputFile : inputFiles) {
            try {
                processor.processFile(inputFile, statistics);
            } catch (IOException e) {
                System.out.println("Ошибка при обработке файла: " + inputFile);
            }
        }

        try {
            processor.writeToFile(statistics.getIntegers(), "integers.txt");
            processor.writeToFile(statistics.getFloats(), "floats.txt");
            processor.writeToFile(statistics.getStrings(), "strings.txt");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при попытке записать информацию в результирующие файлы");
        }

        statistics.printStatistics();
    }
}