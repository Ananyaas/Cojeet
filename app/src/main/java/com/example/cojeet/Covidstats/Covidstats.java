package com.example.cojeet.Covidstats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cojeet.Covidnews.Covid_news;
import com.example.cojeet.Covidstats.covidstatsapi.*;
import com.example.cojeet.Covidstats.covidstatsapi.RegionDatum;
import com.example.cojeet.Menu;
import com.example.cojeet.R;
import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Covidstats extends AppCompatActivity {

    CardView cardViews;
    RecyclerView recyclerView;
    //SwipeRefreshLayout swipeRefreshLayout;
    Dialog dialog;
    Adapter adapter;
    private PieChart mbarchart;
    TextView tcases,rcase,dcases,acases,testcases,lastupdate;
    //List<com.example.cojeet.Covidstats.covidstatsapi.Covidstats> covistats=new ArrayList<>();
    List<RegionDatum> regiondata=new ArrayList<>();
    ArrayList<PieEntry> BarEntryArrayList=new ArrayList<>();
    ArrayList<String> labelsNames=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidstats);
        mbarchart=findViewById(R.id.barchart);
        tcases=findViewById(R.id.totalcases1);
        acases=findViewById(R.id.activecases1);
        dcases=findViewById(R.id.deathcases1);
        testcases=findViewById(R.id.tests1);
        rcase=findViewById(R.id.recoveredcases1);
        lastupdate=findViewById(R.id.lastupdated1);
        recyclerView = findViewById(R.id.list1);

        dialog = new Dialog(Covidstats.this);

        //swipeRefreshLayout = findViewById(R.id.swiperefresh1);
        cardViews=findViewById(R.id.cardView1);
        BarEntryArrayList.clear();
        labelsNames.clear();
        retrieveJson();

    }

    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Menu.class));
    }

    public void retrieveJson(){

        Call<com.example.cojeet.Covidstats.covidstatsapi.Covidstats> call=ApiClient.getInstance().getApi().getData(true);
        call.enqueue(new Callback<com.example.cojeet.Covidstats.covidstatsapi.Covidstats>() {
            @Override
            public void onResponse(Call<com.example.cojeet.Covidstats.covidstatsapi.Covidstats> call, Response<com.example.cojeet.Covidstats.covidstatsapi.Covidstats> response) {
                //swipeRefreshLayout.setRefreshing(false);
                tcases.setText(Integer.toString(response.body().getTotalCases()));
                rcase.setText(Integer.toString(response.body().getRecovered()));
                acases.setText(Integer.toString(response.body().getActiveCases()));
                testcases.setText(Integer.toString(response.body().getPreviousDayTests()));
                dcases.setText(Integer.toString(response.body().getDeaths()));
                lastupdate.setText(response.body().getLastUpdatedAtApify());

                recyclerView.setLayoutManager(new LinearLayoutManager(Covidstats.this));
                regiondata.clear();
                regiondata = response.body().getRegionData();
                adapter = new Adapter(Covidstats.this,regiondata);

                recyclerView.setAdapter(adapter);

                for(int i=0;i<regiondata.size();i++){
                    Log.d("mytagbar", "onRespose:"+regiondata.get(i).getRegion());
                    int totalinfected=regiondata.get(i).getTotalInfected();
                    String state=regiondata.get(i).getRegion().toString();
                    labelsNames.add(state);
                    //BarEntryArrayList.add(new BarEntry(i,totalinfected));
                    BarEntryArrayList.add(new PieEntry(totalinfected,state,"data"));

                }


              /*BarDataSet barDataSet=new BarDataSet(BarEntryArrayList,"total infected population");

              barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
              Description description=new Description();
              description.setText("states");

              BarData barData=new BarData(barDataSet);
              mbarchart.setData(barData);
              mbarchart.setRotationEnabled(true);*/
                PieDataSet barDataSet=new PieDataSet(BarEntryArrayList,"total infected population");

                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                Description description=new Description();
                description.setText("states");

                PieData barData=new PieData(barDataSet);
                mbarchart.setEntryLabelTextSize(3f);
                barData.setValueTextSize(3f);
                mbarchart.setData(barData);
                mbarchart.setRotationEnabled(false);


               /*XAxis xAxis=mbarchart.getXAxis();
               xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsNames));
               xAxis.setPosition(XAxis.XAxisPosition.TOP);
               xAxis.setLabelCount(labelsNames.size());
               xAxis.setLabelRotationAngle(270);
               mbarchart.animateY(2000);
               mbarchart.invalidate();*/

               /* rcase.setText(response.body().getRecovered().toString());
                dcases.setText(response.body().getDeaths().toString());
                acases.setText(response.body().getActiveCases().toString());
                testcases.setText(response.body().getPreviousDayTests().toString());*/
            }

            @Override
            public void onFailure(Call<com.example.cojeet.Covidstats.covidstatsapi.Covidstats> call, Throwable t) {
                //swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_LONG).show();
            }
        });

    }





}