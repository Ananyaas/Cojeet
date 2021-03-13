package com.example.cojeet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cojeet.Covidnews.Covid_news;
import com.example.cojeet.Covidstats.Covidstats;
import com.example.cojeet.login.Signup;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    public CardView hospital,covidnews,editprofile,vaccine,cases,selfchecker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        hospital=(CardView)findViewById(R.id.hospital);
        covidnews=(CardView)findViewById(R.id.covidnews);
        editprofile=(CardView)findViewById(R.id.editprofile);
        vaccine=(CardView)findViewById(R.id.vaccine);
        cases=(CardView)findViewById(R.id.cases);
        selfchecker=(CardView)findViewById(R.id.selfchecker);

        hospital.setOnClickListener(this);
        covidnews.setOnClickListener(this);
        editprofile.setOnClickListener(this);
        vaccine.setOnClickListener(this);
        cases.setOnClickListener(this);
        selfchecker.setOnClickListener(this);




        //Intent to covidnews.class
        //Intent to covid statistics.class
    }
    @Override
    public void onBackPressed() {
        this.finishAffinity();

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.hospital:
                intent=new Intent(this,Menu.class);
                startActivity(intent);
                break;
             case R.id.cases :
                 intent=new Intent(this, Covidstats.class);
                 startActivity(intent);
                 break;
            case R.id.selfchecker:
                intent=new Intent(this,symptomassessment.class);
                startActivity(intent);
                break;
            case R.id.vaccine:
                intent=new Intent(this,Menu.class);
                startActivity(intent);
                break;
            case R.id.covidnews:
                intent=new Intent(this, Covid_news.class);
                startActivity(intent);
                break;
            case R.id.editprofile:
                intent=new Intent(this, Signup.class);
                startActivity(intent);
                break;
        }

    }
}