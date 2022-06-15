package com.kib.order.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",")
public class OrderCSV {

    @DataField(pos = 1)
    private String id;
    @DataField(pos = 2)
    private String area;
    @DataField(pos = 3)
    private String name;
    @DataField(pos = 4)
    private int quantity;
    @DataField(pos = 5)
    private String brand;

    public OrderCSV() {}

    public OrderCSV(String id, String area, String name, int quantity, String brand) {
        this.id = id;
        this.area = area;
        this.name = name;
        this.quantity = quantity;
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return String.format("[id=%s, area=%s, name=%s, quantity=%d, brand=%s]",
                id, area, name, quantity, brand);
    }
}
