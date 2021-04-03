package com.ossovita.currencytracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ossovita.currencytracker.R;
import com.ossovita.currencytracker.model.TrackedCurrency;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.MyViewHolder> {

    private List<TrackedCurrency> trackedCurrencyList;

    public CurrencyAdapter(List<TrackedCurrency> trackedCurrencyList) {
        this.trackedCurrencyList = trackedCurrencyList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(trackedCurrencyList.get(position).getName()+": "+trackedCurrencyList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return trackedCurrencyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
