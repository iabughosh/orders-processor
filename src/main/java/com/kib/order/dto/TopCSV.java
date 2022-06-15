package com.kib.order.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",")
public class TopCSV {

    @DataField(pos = 1)
    private final String name;
    @DataField(pos = 2)
    private final String brand;

    private int totalOrders;

    public TopCSV(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    @Override
    public String toString() {
        return "TopCSV{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
