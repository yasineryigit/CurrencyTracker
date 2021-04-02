package com.ossovita.currencytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ossovita.currencytracker.api.CurrencyApi;
import com.ossovita.currencytracker.api.CurrencyService;
import com.ossovita.currencytracker.model.CurrencyModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    CurrencyApi currencyApi;
    List<CurrencyModel> currencyModel = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyApi = CurrencyService.getInstance().create(CurrencyApi.class);

        getData();

    }

    private void getData() {
        currencyApi.getCurrencies("3fd4971c7c6eb859eaae3ee81cb44d59").enqueue(new Callback<List<CurrencyModel>>() {
            @Override
            public void onResponse(Call<List<CurrencyModel>> call, Response<List<CurrencyModel>> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: hata: " + response.code());
                }
                currencyModel = response.body();
                for(CurrencyModel currencyModel : currencyModel){
                    Log.d(TAG, "onResponse: cevap: " + currencyModel.getRates().toString());
                }

            }

            @Override
            public void onFailure(Call<List<CurrencyModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: hata" + t.getMessage());
            }
        });
    }
}