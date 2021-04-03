package com.example.cojeet.hospitals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.cojeet.Menu;
import com.example.cojeet.R;
import com.example.cojeet.hospitals.Apidata.Example;
import com.example.cojeet.hospitals.Apidata.Feature;
import com.example.cojeet.hospitals.hospitalmodels.Adapter;

import com.example.cojeet.login.Signup2;
import com.github.mikephil.charting.data.PieEntry;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import cz.msebera.android.httpclient.entity.mime.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.abs;

public class Hospitals extends AppCompatActivity {
    Workbook workbook;
    AsyncHttpClient asyncHttpClient;
    List<String> hospitalname,hospital_loc,contacty,facility;
    List<Double> latitude_geo,longitude_geo;

    RecyclerView recyclerView;

    //SwipeRefreshLayout swipeRefreshLayout;
    Dialog dialog;
    Adapter adapter;


    public Double latitude,longitude;
    FusedLocationProviderClient fusedLocationProviderClient;

    //List<com.example.cojeet.Covidstats.covidstatsapi.Covidstats> covistats=new ArrayList<>();
    List<Feature> features = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        hospitalname = new ArrayList<>();
        hospital_loc = new ArrayList<>();
        contacty = new ArrayList<>();
        facility = new ArrayList<>();
        latitude_geo=new ArrayList<>();
        longitude_geo=new ArrayList<>();
        recyclerView = findViewById(R.id.list2);


        dialog = new Dialog(Hospitals.this);

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(Hospitals.this);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            Log.e("Version","Correct");
            if(ActivityCompat.checkSelfPermission(Hospitals.this,
                    Manifest.permission.ACCESS_FINE_LOCATION )== PackageManager.PERMISSION_GRANTED){
                Log.e("Version","Permission Given");
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location=task.getResult();
                        //Log.e("Version","latitude= "+lat);
                        if(location!=null){
                            Geocoder geocoder=new Geocoder(Hospitals.this, Locale.getDefault());
                            Log.e("Version","location given");
                            latitude=location.getLatitude();
                            longitude=location.getLongitude();

                            //addresses=geocoder.getFromLocation(lat,lon,1);
                            //state=addresses.get(0).getAddressLine(0);
                            //state=s.cont(state);
                            //Log.e("Version",state);

                            //loc="lat="+lat+" long="+lon;
                            try {
                                AssetManager assetManager = getAssets();
                                InputStream is = assetManager.open("hospfinal.xls");
                                Workbook workbook = Workbook.getWorkbook(is);
                                Sheet s = workbook.getSheet(0);
                                for (int i = 1; i < s.getRows(); i++) {
                                    Cell[] row = s.getRow(i);
                                    if(abs(Double.parseDouble(row[12].getContents())-latitude)<=0.2 && abs(Double.parseDouble(row[13].getContents())-longitude)<=0.2) {
                                        hospitalname.add(row[1].getContents());
                                        hospital_loc.add(((!row[2].getContents().equals(""))?(row[2].getContents()+" "):"" )+ ((!row[3].getContents().equals(""))?(row[3].getContents()+" "):"") + ((!row[4].getContents().equals(""))?(row[4].getContents()+" "):"") + ((!row[5].getContents().equals(""))?(row[5].getContents()+" "):"") + ((!row[6].getContents().equals(""))?(row[6].getContents()+" "):"") +((!row[11].getContents().equals(""))?(row[11].getContents()+" "):"") + ((!row[10].getContents().equals(""))?(row[10].getContents()+" "):"") + row[9].getContents());
                                        contacty.add(((!row[7].getContents().equals(""))?row[7].getContents():"not available"));
                                        facility.add(row[8].getContents());
                                        latitude_geo.add(Double.parseDouble(row[12].getContents()));
                                        longitude_geo.add(Double.parseDouble(row[13].getContents()));
                                    }
                                    else
                                        continue;
                                }

                                showData();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            else {
                ActivityCompat.requestPermissions(Hospitals.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
            }
        }

        Log.e("Version","longitude="+longitude);
        Log.e("Version","latitude= "+latitude);
        //swipeRefreshLayout = findViewById(R.id.swiperefresh1);
       /* fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(Hospitals.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getlocation();
            Log.d("my location", latitude.toString()+" "+longitude.toString());
        }*/






    }
    private void showData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,hospitalname,hospital_loc,contacty,facility,latitude_geo,longitude_geo,latitude,longitude);
        recyclerView.setAdapter(adapter);
    }


   /*private void getlocation() {
        @SuppressLint("MissingPermission") Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    latitude=location.getLatitude();
                    longitude=location.getLongitude();

                }
            }
        });
    }*/

    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Menu.class));
    }

   /* public void retrieveJson(){

        Call<Example> call= ApiClient.getInstance().getApi().gethospitalData(11.045766,78.511082);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                recyclerView.setLayoutManager(new LinearLayoutManager(Hospitals.this));
                features.clear();
                features=response.body().getFeatures();
                Log.d("mylist", "onResponse: "+features.get(0).getAttributes());
                adapter=new Adapter(Hospitals.this,features);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_LONG).show();
            }
        });


    }*/





}
