package com.example.cojeet.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

import com.example.cojeet.Covidstats.Covidstats;
import com.example.cojeet.Menu;
import com.example.cojeet.R;
import com.example.cojeet.hospitals.Hospitals;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }


}
