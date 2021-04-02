package com.ossovita.currencytracker.model;

public class TrackedCurrency {
    String name;
    Double price;

    public TrackedCurrency(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "TrackedCurrency{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
