 package com.example.cojeet.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cojeet.MainActivity;
import com.example.cojeet.Menu;
import com.example.cojeet.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.*;

public class Signup2 extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationProviderClient;
    States s;
    Geocoder geocoder;
    Double lat,lon;
    DatabaseReference ref;
    User_Detail ud;
    List<Address> addresses;
    EditText Age,Height,Weight;
    RadioButton M,F,O,y,n,p,neg;
    Button medsign;
    DBHelper DB;
    RadioGroup gen,cov,vac;
    String medhelp,gender,Email,Name,Contact, mhis,cont,vaccine,corona,loc,age,height,weight,state;
    CheckBox Fever,Dcough,Chestp,Tiredness,Diarrhoea,Conjectiv,Shortob,Anp,Lossos,Sorethroat,Allergy,Immuno,Preg,Blood,Disease,BP,Fibrosis,h1,h2,h3,h4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        Bundle bundle = getIntent().getExtras();
        Email= bundle.getString ("Email");
        Name=bundle.getString ("name");
        Contact=bundle.getString("Contact");
        ref= FirebaseDatabase.getInstance().getReference().child("User_Detail");
        ud=new User_Detail();

        loc="delhi";
        s=new States();
        s.init();

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(Signup2.this);
        //geocoder=new Geocoder(Signup2.this, Locale.getDefault());


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
            Log.e("Version","Correct");
            if(ActivityCompat.checkSelfPermission(Signup2.this,
                    permission.ACCESS_FINE_LOCATION )== PackageManager.PERMISSION_GRANTED){
                Log.e("Version","Permission Given");
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location=task.getResult();
                        //Log.e("Version","latitude= "+lat);
                        if(location!=null){
                            Geocoder geocoder=new Geocoder(Signup2.this,Locale.getDefault());
                            Log.e("Version","location given");
                            try {
                                lat=location.getLatitude();
                                lon=location.getLongitude();
                                Log.e("Version","longitude="+lon);
                                Log.e("Version","latitude= "+lat);
                                addresses=geocoder.getFromLocation(lat,lon,1);
                                state=addresses.get(0).getAddressLine(0);
                                state=s.cont(state);
                                Log.e("Version",state);

                                //loc="lat="+lat+" long="+lon;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            else {
               ActivityCompat.requestPermissions(Signup2.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
            }
        }






        medsign=findViewById(R.id.signupbutton2);
        DB = new DBHelper(this);

        medsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storedata(state,lat,lon);
            }
        });
    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    private void storedata(String state,Double lat,Double lon) {
        age=Age.getText().toString();
        weight=Weight.getText().toString();
        height=Height.getText().toString();



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
            gender=M.getText().toString();
        }
        if(F.isChecked()){
            gender=F.getText().toString();
        }
        if(O.isChecked()){
            gender=O.getText().toString();
        }
        if (cov.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(Signup2.this,"please select covid status",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(p.isChecked()){
            corona=p.getText().toString().trim();
        }
        if(neg.isChecked()){
            corona=n.getText().toString().trim();
        }
        if (vac.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(Signup2.this,"please select vaccination status",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(y.isChecked()){
            vaccine=y.getText().toString();
        }
        if(n.isChecked()){
            vaccine=n.getText().toString();
        }

        if(Fever.isChecked()){
            medhelp+=Fever.getText().toString()+",";
        }
        if(Dcough.isChecked()){
            medhelp+=Dcough.getText().toString()+",";
        }
        if(Tiredness.isChecked()){
            medhelp+=Tiredness.getText().toString()+",";
        }
        if(Chestp.isChecked()){
            medhelp+=Chestp.getText().toString()+",";
        }
        if(Sorethroat.isChecked()){
            medhelp+=Sorethroat.getText().toString()+",";
        }
        if(Diarrhoea.isChecked()){
            medhelp+=Diarrhoea.getText().toString()+",";
        }
        if(Conjectiv.isChecked()){
            medhelp+=Conjectiv.getText().toString()+",";
        }
        if(Shortob.isChecked()){
            medhelp+=Shortob.getText().toString()+",";
        }
        if(Anp.isChecked()){
            medhelp+=Anp.getText().toString()+",";
        }
        if(Lossos.isChecked()){
            medhelp+=Lossos.getText().toString()+",";
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
        if(h1.isChecked()){
            cont+=h1.getText().toString();
        }
        if(h2.isChecked()){
            cont+=h2.getText().toString();
        }
        if(h3.isChecked()){
            cont+=h3.getText().toString();
        }
        if(h4.isChecked()){
            cont+=h4.getText().toString();
        }

        ud.setUsername(Name);
        ud.setEmail(Email);
        ud.setContact(Contact);
        ud.setAge(age);
        ud.setGender(gender);
        ud.setHeight(height);
        ud.setWeight(weight);
        ud.setCoronaStatus(corona);
        ud.setVaccinationStatus(vaccine);
        ud.setSymptoms(medhelp);
        ud.setHealthCondition(mhis);
        ud.setContactHistory(cont);
        ud.setLat(lat);
        ud.setLon(lon);
        ud.setState(state);
        ref.push().setValue(ud);
        Toast.makeText(Signup2.this,"Signed in successfully",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), Menu.class));

      /* boolean x=DB.insertData2(Name,Email,Contact,age,gender,height,weight,corona,vaccine,medhelp,mhis,cont,loc);
        if(x==true)
        {
            Toast.makeText(Signup2.this,"Signed in successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), Menu.class));
        }
        else
            Toast.makeText(Signup2.this,loc+"Error",Toast.LENGTH_LONG).show(); */
    }
}