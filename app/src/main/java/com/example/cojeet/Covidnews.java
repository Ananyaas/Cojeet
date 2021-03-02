package com.example.cojeet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cojeet.api.ApiClient;
import com.example.cojeet.models.Articles;
import com.example.cojeet.models.Headlines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Queue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Covidnews extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;


    Dialog dialog;
    final String API_KEY = "27221be1546b47a88eda7dd572b88043";
    Adapter adapter;
    List<Articles>  articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidnews);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recyclerView);


        dialog = new Dialog(Covidnews.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String country = getCountry();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson("COVID",country,API_KEY);
                retrieveJson("Corona",country,API_KEY);
                retrieveJson("corona",country,API_KEY);
                retrieveJson("vaccine",country,API_KEY);
                retrieveJson("Vaccine",country,API_KEY);
                retrieveJson("pandemic",country,API_KEY);
                retrieveJson("virus",country,API_KEY);
            }
        });
        retrieveJson("COVID",country,API_KEY);
        retrieveJson("Corona",country,API_KEY);
        retrieveJson("corona",country,API_KEY);
        retrieveJson("vaccine",country,API_KEY);
        retrieveJson("Vaccine",country,API_KEY);
        retrieveJson("pandemic",country,API_KEY);
        retrieveJson("virus",country,API_KEY);

    }

    public void retrieveJson(String query ,String country, String apiKey){


        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call;
        call= ApiClient.getInstance().getApi().getSpecificData(query,apiKey);

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(Covidnews.this,articles);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Covidnews.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }


}