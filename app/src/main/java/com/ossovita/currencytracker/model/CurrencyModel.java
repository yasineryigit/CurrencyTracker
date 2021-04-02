package com.ossovita.currencytracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrencyModel {
    String timestamp;
    List<Rates> rates;

    public CurrencyModel(String timestamp, List<Rates> rates) {
        this.timestamp = timestamp;
        this.rates = rates;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public List<Rates> getRates() {
        return rates;
    }
}
