package com.ossovita.currencytracker.model;

public class Rates {
    String name;
    String price;

    public Rates(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
