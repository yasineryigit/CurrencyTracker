package com.ossovita.currencytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;

import com.ossovita.currencytracker.api.CurrencyApi;
import com.ossovita.currencytracker.api.CurrencyService;
import com.ossovita.currencytracker.model.CurrencyModel;
import com.ossovita.currencytracker.model.TrackedCurrency;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    CurrencyApi currencyApi;
    List<TrackedCurrency> trackedCurrencyList = new ArrayList<>();
    CurrencyModel currencyModel;
    Double EURUSD,EURGBP,EURJPY;
    Double XEURUSD,XEURGBP,XEURJPY;
    Double USDTRY,EURTRY,GBPTRY,JPYTRY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyApi = CurrencyService.getInstance().create(CurrencyApi.class);

        getData();

    }

    private void getData() {
        currencyApi.getCurrencies("3fd4971c7c6eb859eaae3ee81cb44d59").enqueue(new Callback<CurrencyModel>() {
            @Override
            public void onResponse(Call<CurrencyModel> call, Response<CurrencyModel> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: hata: " + response.code());
                }
                currencyModel = response.body();
                //FOR USDTRY
                XEURUSD=1/currencyModel.getRates().getUsd();
                USDTRY = XEURUSD*currencyModel.getRates().getTry();

                //FOR GBP TRY
                XEURGBP=1/currencyModel.getRates().getGbp();
                GBPTRY=XEURGBP*currencyModel.getRates().getTry();

                //FOR JPYTRY
                XEURJPY=1/currencyModel.getRates().getJpy();
                JPYTRY=XEURJPY*currencyModel.getRates().getTry();

                //FOR EURTRY
                EURTRY = currencyModel.getRates().getTry();

                trackedCurrencyList.add(new TrackedCurrency("USDTRY",USDTRY));
                trackedCurrencyList.add(new TrackedCurrency("EURTRY",EURTRY));
                trackedCurrencyList.add(new TrackedCurrency("GBPTRY",GBPTRY));
                trackedCurrencyList.add(new TrackedCurrency("JPYTRY",JPYTRY));

                for(TrackedCurrency trackedCurrency : trackedCurrencyList){
                    Log.d(TAG, "onResponse: name " + trackedCurrency.getName() +
                            " Price: " + trackedCurrency.getPrice()
                            );
                }

            }

            @Override
            public void onFailure(Call<CurrencyModel> call, Throwable t) {
                Log.d(TAG, "onFailure: hata" + t.getMessage());
            }
        });

    }

}