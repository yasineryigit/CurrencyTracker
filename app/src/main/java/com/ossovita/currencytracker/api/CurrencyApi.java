package com.ossovita.currencytracker.api;

import com.ossovita.currencytracker.model.CurrencyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyApi  {
    @GET("latest")
    Call<List<CurrencyModel>> getCurrencies(
            @Query("access_key") String key);



}
