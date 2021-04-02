package com.ossovita.currencytracker.api;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyService {
    //bu classtan singleton yapıda retrofit objesi alacağız.
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://data.fixer.io/api/";

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
