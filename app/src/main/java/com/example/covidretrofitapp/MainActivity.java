package com.example.covidretrofitapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CountryCodePicker countryCodePicker;
    TextView  total,todayTotal,deaths,todayDeaths,recovered,todayRecovered,active,todayActive;

    String country;
    String[]type={"cases","deaths","recovered","active"};
    TextView mFilter;
    Spinner spinner;
    PieChart pieChart;
    private RecyclerView recyclerView;
    DataAdapter adapter;

    private ArrayList<CovidClass>CovidClass1;
    private ArrayList<CovidClass>CovidClass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        countryCodePicker=findViewById(R.id.pickerId);
        total=findViewById(R.id.totalActive);
        todayTotal=findViewById(R.id.todayTotal);
        deaths=findViewById(R.id.totalDeaths);
        todayDeaths=findViewById(R.id.todayDeaths);
        recovered=findViewById(R.id.totalRecovered);
        todayRecovered=findViewById(R.id.todayRecovered);
        active=findViewById(R.id.ActiveCase);
        todayActive=findViewById(R.id.todayActive);
        mFilter=findViewById(R.id.filter);
        recyclerView=findViewById(R.id.recyclerView);
        pieChart=findViewById(R.id.pieChart);
        spinner=findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,type);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        spinner.setSelection(0,true);

        CovidClass1=new ArrayList<>();
        CovidClass2=new ArrayList<>();


        ApiClient.getApiInterface().getCountryData().enqueue(new Callback<List<com.example.covidretrofitapp.CovidClass>>() {
            @Override
            public void onResponse(Call<List<com.example.covidretrofitapp.CovidClass>> call, Response<List<com.example.covidretrofitapp.CovidClass>> response) {
                CovidClass2.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<com.example.covidretrofitapp.CovidClass>> call, Throwable t) {

            }
        });

        adapter=new DataAdapter(this,CovidClass2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        countryCodePicker.setAutoDetectedCountry(true);
        country=countryCodePicker.getSelectedCountryName();
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country=countryCodePicker.getSelectedCountryName();
                fetchData();
            }

        });
        fetchData();


    }

    private void fetchData() {
        ApiClient.getApiInterface().getCountryData().enqueue(new Callback<List<com.example.covidretrofitapp.CovidClass>>() {
            @Override
            public void onResponse(Call<List<com.example.covidretrofitapp.CovidClass>> call, Response<List<com.example.covidretrofitapp.CovidClass>> response) {
                CovidClass1.addAll(response.body());

                for(int i=0;i< CovidClass1.size();i++){
                    if(CovidClass1.get(i).getCountry().equals(country)){
                        total.setText(CovidClass1.get(i).getCases());
                        todayTotal.setText(CovidClass1.get(i).getTodayCases());
                        recovered.setText(CovidClass1.get(i).getRecovered());
                        todayRecovered.setText(CovidClass1.get(i).getTodayRecovered());
                        active.setText(CovidClass1.get(i).getActive());
                        todayActive.setText(CovidClass1.get(i).getTodayActive());
                        deaths.setText(CovidClass1.get(i).getDeaths());
                        todayDeaths.setText(CovidClass1.get(i).getTodayDeaths());


                        //System.out.println("Sonuc"+CovidClass1.get(i).getActive());
                       // Log.d("Sout",CovidClass1.get(i).getActive());
                        float activeString,totalString,recoveredString,deathsString;

                        activeString=Integer.parseInt(CovidClass1.get(i).getActive());
                        totalString=Integer.parseInt(CovidClass1.get(i).getCases());
                        recoveredString=Integer.parseInt(CovidClass1.get(i).getRecovered());
                        deathsString=Integer.parseInt(CovidClass1.get(i).getDeaths());
                        updateGraph(activeString,totalString,recoveredString,deathsString);


                    }


                }
            }

            @Override
            public void onFailure(Call<List<com.example.covidretrofitapp.CovidClass>> call, Throwable t) {

            }
        });
    }

    private void updateGraph(float activeString, float totalString, float recoveredString, float deathsString) {
        pieChart.clearChart();

        int[] colorList =  new int[]{Color.parseColor("#ff8800"),
                Color.parseColor("#cc0000"),
                Color.parseColor("#2196f3"),
                Color.parseColor("#03dac5")};
        pieChart.addPieSlice(new PieModel(totalString,colorList[0]));
        pieChart.addPieSlice(new PieModel(activeString,colorList[1]));
        pieChart.addPieSlice(new PieModel(recoveredString,colorList[2]));
        pieChart.addPieSlice(new PieModel(deathsString,colorList[3]));
        pieChart.startAnimation();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String filterTypes=type[position];
        mFilter.setText(filterTypes);
        adapter.filter(filterTypes);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}