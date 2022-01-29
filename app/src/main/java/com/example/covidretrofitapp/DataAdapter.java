package com.example.covidretrofitapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    Context context;
    ArrayList<CovidClass>covidClassList;
    int m=1;

    public DataAdapter(Context context, ArrayList<CovidClass> covidClassList) {
        this.context = context;
        this.covidClassList = covidClassList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CovidClass covidClass=covidClassList.get(position);
        if(m==1){
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(covidClass.getCases())));
        }else if(m==2){
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(covidClass.getRecovered())));
        }else if(m==3){
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(covidClass.getDeaths())));
        }else{
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(covidClass.getActive())));
        }
        holder.country.setText(covidClass.getCountry());

    }

    @Override
    public int getItemCount() {
        return covidClassList.size();
    }

    public void filter(String filterTypes) {
        if (filterTypes.equals("cases")) {
            m=1;
        } else if (filterTypes.equals("recovered")) {
            m=2;
        }else if (filterTypes.equals("deaths")) {
            m=3;
        }else {
            m=4;
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cases,country;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cases=itemView.findViewById(R.id.countryCase);
            country=itemView.findViewById(R.id.countryName);

        }
    }
}
