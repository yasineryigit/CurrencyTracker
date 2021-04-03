package com.ossovita.currencytracker.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;

import com.ossovita.currencytracker.R;
import com.ossovita.currencytracker.adapter.CurrencyAdapter;
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
    private RecyclerView recyclerView;
    private CurrencyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyApi = CurrencyService.getInstance().create(CurrencyApi.class);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                //for testing
                for(TrackedCurrency trackedCurrency : trackedCurrencyList){
                    Log.d(TAG, "onResponse: name " + trackedCurrency.getName() +
                            " Price: " + trackedCurrency.getPrice());
                }

                adapter=new CurrencyAdapter(trackedCurrencyList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CurrencyModel> call, Throwable t) {
                Log.d(TAG, "onFailure: hata" + t.getMessage());
            }
        });

    }

    private double calculate (String currency1, String currency2) {
        String parityString = currency1+currency2;

        Double parity = 1/currencyModel.getRates().getUsd()*currencyModel.getRates().getTry();

        return 0;
    }

}