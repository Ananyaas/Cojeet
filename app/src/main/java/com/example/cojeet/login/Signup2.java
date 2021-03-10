package com.example.cojeet.login;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cojeet.Menu;
import com.example.cojeet.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Locale;

import static android.Manifest.*;

public class Signup2 extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationProviderClient;
    Geocoder geocoder;
    Double lat,lon;
    List<Address> addresses;
    EditText Age,Height,Weight;
    RadioButton M,F,O,y,n,p,neg;
    Button medsign;
    DBHelper DB;
    RadioGroup gen,cov,vac;
    String medhelp,gender,Email,Name,Contact, mhis,cont,vaccine,corona,loc;
    CheckBox Fever,Dcough,Chestp,Tiredness,Diarrhoea,Conjectiv,Shortob,Anp,Lossos,Sorethroat,Allergy,Immuno,Preg,Blood,Disease,BP,Fibrosis,h1,h2,h3,h4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loc="Not found";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        Bundle bundle = getIntent().getExtras();
        Email= bundle.getString ("Email");
        Name=bundle.getString ("name");
        Contact=bundle.getString("Contact");

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        geocoder=new Geocoder(this, Locale.getDefault());


        Age=findViewById(R.id.ed1);
        Weight=findViewById(R.id.ed2);
        Height=findViewById(R.id.ed4);


        M=findViewById(R.id.rb12);
        F=findViewById(R.id.rb13);
        O=findViewById(R.id.rb11);
        y=findViewById(R.id.yes);
        n=findViewById(R.id.no);
        p=findViewById(R.id.csp);
        neg=findViewById(R.id.csn);
        gen=findViewById(R.id.gen);
        cov=findViewById(R.id.covidstatus);
        vac=findViewById(R.id.vaccination);

        //Symptom Checkboxes

        Fever=findViewById(R.id.cb1);
        Dcough=findViewById(R.id.cb2);
        Tiredness=findViewById(R.id.cb3);
        Chestp=findViewById(R.id.cb4);
        Sorethroat=findViewById(R.id.cb5);
        Diarrhoea=findViewById(R.id.cb6);
        Conjectiv=findViewById(R.id.cb7);
        Shortob=findViewById(R.id.cb8);
        Anp=findViewById(R.id.cb9);
        Lossos=findViewById(R.id.cb10);

        //medical history checkboxes

        Allergy=findViewById(R.id.cb12);
        Preg=findViewById(R.id.cb13);
        Immuno=findViewById(R.id.cb14);
        Blood=findViewById(R.id.cb15);
        Disease=findViewById(R.id.cb16);
        BP=findViewById(R.id.cb17);
        Fibrosis=findViewById(R.id.cb18);

        //contact history checkboxes

        h1=findViewById(R.id.cb19);
        h2=findViewById(R.id.cb20);
        h3=findViewById(R.id.cb21);
        h4=findViewById(R.id.cb22);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(getApplicationContext().checkSelfPermission(permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null){
                            lat=location.getLatitude();
                            lon=location.getLongitude();
                        }
                    }
                });


            }
            else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
        try{
            addresses=geocoder.getFromLocation(lat,lon,1);
            String add=addresses.get(0).getAddressLine(0);
            String area=addresses.get(0).getLocality();
            String city=addresses.get(0).getAdminArea();
            String coun=addresses.get(0).getCountryName();
            String pc=addresses.get(0).getPostalCode();
            loc=add+","+area+","+city+","+coun+","+pc;
        }
        catch (Exception e){
            e.printStackTrace();
        }





        medsign=findViewById(R.id.signupbutton2);
        DB = new DBHelper(this);

        medsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storedata();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    private void storedata() {
        String age=Age.getText().toString();
        String weight=Weight.getText().toString();
        String height=Height.getText().toString();



        if(TextUtils.isEmpty(age)){
            Age.setError("Field required");
            return;
        }
        if(TextUtils.isEmpty(weight)){
            Weight.setError("Field required");
            return;
        }
        if(TextUtils.isEmpty(height)){
            Height.setError("Field required");
            return;
        }


        if (gen.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(Signup2.this,"please select gender",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(M.isChecked()){
            gender="male";
        }
        if(F.isChecked()){
            gender="female";
        }
        if(O.isChecked()){
            gender="other";
        }
        if (cov.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(Signup2.this,"please select covid status",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(p.isChecked()){
            corona="pos";
        }
        if(n.isChecked()){
            corona="neg";
        }
        if (vac.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(Signup2.this,"please select vaccination status",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(y.isChecked()){
            vaccine="yes";
        }
        if(n.isChecked()){
            vaccine="no";
        }

        if(Fever.isChecked()){
            medhelp+=Fever.getText()+",";
        }
        if(Dcough.isChecked()){
            medhelp+="Dry Cough"+",";
        }
        if(Tiredness.isChecked()){
            medhelp+="Tiredness"+",";
        }
        if(Chestp.isChecked()){
            medhelp+="Chest Pain"+",";
        }
        if(Sorethroat.isChecked()){
            medhelp+="Sorethroat"+",";
        }
        if(Diarrhoea.isChecked()){
            medhelp+="Diarrhoea"+",";
        }
        if(Conjectiv.isChecked()){
            medhelp+="Conjunctivitis"+",";
        }
        if(Shortob.isChecked()){
            medhelp+="Short Of Breath"+",";
        }
        if(Anp.isChecked()){
            medhelp+="Aches and Pains"+",";
        }
        if(Lossos.isChecked()){
            medhelp+="Loss Of speech and movement"+",";
        }
        if(Preg.isChecked()){
            mhis+=Preg.getText()+"/";
        }
        if(Allergy.isChecked()){
            mhis+=Allergy.getText()+"/";
        }
        if(Blood.isChecked()){
            mhis+=Blood.getText()+"/";
        }
        if(BP.isChecked()){
            mhis+=BP.getText()+"/";
        }
        if(Immuno.isChecked()){
            mhis+=Immuno.getText()+"/";
        }
        if(Disease.isChecked()){
            mhis+=Disease.getText()+"/";
        }
        if(Fibrosis.isChecked()){
            mhis+=Fibrosis.getText()+"/";
        }
        if(h1.isChecked()||h2.isChecked()||h3.isChecked()||h4.isChecked()){
            cont="yes";
        }


        boolean x=DB.insertData2(Name,Email,Contact,age,gender,height,weight,corona,vaccine,medhelp,mhis,cont,loc);
        if(x==true)
        {
            Toast.makeText(Signup2.this,"Signed in successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), Menu.class));
        }
        else
            Toast.makeText(Signup2.this,loc+"Error",Toast.LENGTH_LONG).show();
    }
}