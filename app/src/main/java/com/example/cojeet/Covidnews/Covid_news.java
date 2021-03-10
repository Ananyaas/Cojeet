package com.example.cojeet.Covidnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cojeet.Menu;
import com.example.cojeet.R;
import com.example.cojeet.Covidnews.covidnewsapi.ApiClient;
import com.example.cojeet.Covidnews.models.Articles;
import com.example.cojeet.Covidnews.models.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Covid_news extends AppCompatActivity {

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


        dialog = new Dialog(Covid_news.this);

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
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Menu.class));
    }

    public void retrieveJson(String query ,String country, String apiKey){


        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call;
        call= ApiClient.getInstance().getApi().getSpecificData(query,"publishedAt",apiKey);

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(Covid_news.this,articles);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Covid_news.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }


}