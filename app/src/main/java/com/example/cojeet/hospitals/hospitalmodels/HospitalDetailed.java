package com.example.cojeet.hospitals.hospitalmodels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;

import com.example.cojeet.R;
import com.example.cojeet.hospitals.Hospitals;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class HospitalDetailed extends AppCompatActivity {
    Double user_lat,user_lon;

    Double lat,lon;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detailed);
        //supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);



        Intent intent = getIntent();
        user_lat = intent.getDoubleExtra("user_lat",0.0);
        user_lon = intent.getDoubleExtra("user_lon",0.0);
        lat = intent.getDoubleExtra("lat",0.0);
        lon = intent.getDoubleExtra("lon",0.0);

        Displaytrack(lat,lon,user_lat,user_lon);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(getApplicationContext(),Hospitals.class);
    }
//
    private void Displaytrack(Double lat, Double lon, Double latitude, Double longitude) {
        try {
            Uri uri= Uri.parse("https://www.google.co.in/maps/dir/"+lat.toString()+","+lon.toString()+"/"+latitude.toString()+","+longitude.toString());
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        catch (ActivityNotFoundException r){
            Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }


}