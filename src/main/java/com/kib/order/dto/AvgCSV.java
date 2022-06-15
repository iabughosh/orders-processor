package com.kib.order.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",")
public class AvgCSV {
    @DataField(pos = 1)
    private final String name;
    @DataField(pos = 2, precision = 2)
    private final double average;

    public AvgCSV(String name, double average) {
        this.name = name;
        this.average = average;
    }

    @Override
    public String toString() {
        return "AvgCsv{" +
                "name='" + name + '\'' +
                ", average=" + average +
                '}';
    }
}
