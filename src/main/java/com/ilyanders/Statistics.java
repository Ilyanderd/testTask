package com.ilyanders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Statistics {
    private final boolean shortStats;
    private final boolean fullStats;

    private final List<Long> integers = new ArrayList<>();
    private final List<Double> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public Statistics(boolean shortStats, boolean fullStats) {
        this.shortStats = shortStats;
        this.fullStats = fullStats;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings.addAll(strings);
    }

    public List<Double> getFloats() {
        return floats;
    }

    public void setFloats(List<Double> floats) {
        this.floats.addAll(floats);
    }

    public List<Long> getIntegers() {
        return integers;
    }

    public void setIntegers(List<Long> integers) {
        this.integers.addAll(integers);
    }

    public void printStatistics() {
        int integerCount = integers.size();
        int floatCount = floats.size();
        int stringCount = strings.size();

        if (shortStats) {
            System.out.println("Краткая статистика:");
            System.out.println("Целых чисел: " + integerCount);
            System.out.println("Вещественных чисел: " + floatCount);
            System.out.println("Строк: " + stringCount);
        }

        if (fullStats) {
            long integerMin = Collections.min(integers);
            long integerMax = Collections.max(integers);
            long integerSum = integers.stream()
                    .mapToLong(Long::longValue)
                    .sum();
            double integerArithmeticMean = (double) integerSum / integerCount;

            double floatMin = Collections.min(floats);
            double floatMax = Collections.max(floats);
            double floatSum = floats.stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
            double floatArithmeticMean = floatSum / floatCount;

            int stringMinLength = strings.stream()
                    .mapToInt(String::length)
                    .min()
                    .orElse(0);

            int stringMaxLength = strings.stream()
                    .mapToInt(String::length)
                    .max()
                    .orElse(0);

            System.out.println("Полная статистика:");

            if (integerCount > 0) {
                System.out.println("Целые числа:");
                System.out.println("Количество чисел: " + integerCount);
                System.out.println("Минимальное число: " + integerMin);
                System.out.println("Максимальное число: " + integerMax);
                System.out.println("Сумма чисел: " + integerSum);
                System.out.println("Среднее арифметическое: " + integerArithmeticMean);
            }

            if (floatCount > 0) {
                System.out.println("Вещественные числа:");
                System.out.println("Количество чисел: " + floatCount);
                System.out.println("Минимальное число: " + floatMin);
                System.out.println("Максимальное число: " + floatMax);
                System.out.println("Сумма чисел: " + floatSum);
                System.out.println("Среднее арифметическое: " + floatArithmeticMean);
            }

            if (stringCount > 0) {
                System.out.println("Строки:");
                System.out.println("Количество строк: " + stringCount);
                System.out.println("Мин. длина: " + stringMinLength);
                System.out.println("Макс. длина: " + stringMaxLength);
            }
        }
    }
}