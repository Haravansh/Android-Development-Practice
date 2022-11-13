package com.harry.countrynames.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harry.countrynames.R;
import com.harry.countrynames.model.CountryModel;
import com.harry.countrynames.model.Result;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private ArrayList<CountryModel> countries;

    public CountryAdapter(ArrayList<CountryModel> countriesList) {
        this.countries = countriesList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.textView.setText(countries.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    //view holder
    class CountryViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.country_textView);
        }
    }
}
