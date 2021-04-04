package com.example.cojeet.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cojeet.Menu;
import com.example.cojeet.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class EditProfile extends AppCompatActivity {
    EditText Age,Height,Weight,Uname,Pass,ConPass,Phone;
    FirebaseUser user;
    DatabaseReference reff;
    RadioButton M,F,O,y,n,p,neg;
    Button medsign,logout;
    String s1;
    User_Detail ud;
    RadioGroup gen,cov,vac;

    String medhelp,gender,Email,Name,Contact, mhis="*",cont,vaccine,corona,loc,age,weight,height;
    CheckBox Fever,Dcough,Chestp,Tiredness,Diarrhoea,Conjectiv,Shortob,Anp,Lossos,Sorethroat,Allergy,Immuno,Preg,Blood,Disease,BP,Fibrosis,h1,h2,h3,h4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        //DB = new DBHelper(this);
        ud=new User_Detail();
        user= FirebaseAuth.getInstance().getCurrentUser();
        Email=user.getEmail();
        Toast.makeText(EditProfile.this,Email,Toast.LENGTH_LONG).show();

        reff= FirebaseDatabase.getInstance().getReference().child("User_Detail");
        Query query= reff.orderByChild("email").equalTo(Email);

        Uname=findViewById(R.id.uname);
        Pass=findViewById(R.id.pass);
        ConPass=findViewById(R.id.passc);
        Phone=findViewById(R.id.phn);
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

        Allergy=findViewById(R.id.cb12);
        Preg=findViewById(R.id.cb13);
        Immuno=findViewById(R.id.cb14);
        Blood=findViewById(R.id.cb15);
        Disease=findViewById(R.id.cb16);
        BP=findViewById(R.id.cb17);
        Fibrosis=findViewById(R.id.cb18);

        h1=findViewById(R.id.cb19);
        h2=findViewById(R.id.cb20);
        h3=findViewById(R.id.cb21);
        h4=findViewById(R.id.cb22);



        medsign=findViewById(R.id.signupbutton2);
        logout=findViewById(R.id.logout);



        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String ss=snapshot.getValue().toString();
                String[] s =ss.split("=");
                s1=s[0].substring(1);
                ud.setRef(s1);
                Name=snapshot.child(s1).child("username").getValue().toString();
                Contact=snapshot.child(s1).child("contact").getValue().toString();
                age=snapshot.child(s1).child("age").getValue().toString();
                gender=snapshot.child(s1).child("gender").getValue().toString();
                height=snapshot.child(s1).child("height").getValue().toString();
                weight= snapshot.child(s1).child("weight").getValue().toString();
                corona= snapshot.child(s1).child("coronaStatus").getValue().toString();
                vaccine= snapshot.child(s1).child("vaccinationStatus").getValue().toString();
                medhelp= snapshot.child(s1).child("symptoms").getValue().toString();
                cont= snapshot.child(s1).child("contactHistory").getValue().toString();
                mhis= snapshot.child(s1).child("healthCondition").getValue().toString();
                Toast.makeText(EditProfile.this,Name+Email+ age, Toast.LENGTH_LONG).show();

                //setting initial values
                Uname.setText(Name);

                Phone.setText(Contact);
                Age.setText(age);

                Weight.setText(weight);

                Height.setText(height);


                if(gender.contains(M.getText().toString())){
                    M.setChecked(true);
                }
                else  if(gender.contains(F.getText().toString())){
                    F.setChecked(true);
                }
                else if(gender.contains(O.getText().toString())){
                    O.setChecked(true);
                }

                if(corona.contains(p.getText().toString())){
                    p.setChecked(true);
                }
                else if(corona.contains(neg.getText().toString())){
                    neg.setChecked(true);
                }

                if(vaccine.contains(y.getText().toString())){
                    y.setChecked(true);
                }
                else if(vaccine.contains(n.getText().toString())){
                    n.setChecked(true);
                }


                //Symptom Checkboxes



                if(medhelp.contains(Fever.getText().toString()))
                    Fever.setChecked(true);
                if(medhelp.contains(Dcough.getText().toString()))
                    Dcough.setChecked(true);
                if(medhelp.contains(Tiredness.getText().toString()))
                    Tiredness.setChecked(true);
                if(medhelp.contains(Chestp.getText().toString()))
                    Chestp.setChecked(true);
                if(medhelp.contains(Sorethroat.getText().toString()))
                    Sorethroat.setChecked(true);
                if(medhelp.contains(Diarrhoea.getText().toString()))
                    Diarrhoea.setChecked(true);
                if(medhelp.contains(Conjectiv.getText().toString()))
                    Conjectiv.setChecked(true);
                if(medhelp.contains(Shortob.getText().toString()))
                    Shortob.setChecked(true);
                if(medhelp.contains(Anp.getText().toString()))
                    Anp.setChecked(true);
                if(medhelp.contains(Lossos.getText().toString()))
                    Lossos.setChecked(true);

                //medical history checkboxes



                if(mhis.contains(Allergy.getText().toString()))
                    Allergy.setChecked(true);
                if(mhis.contains(Preg.getText().toString()))
                    Preg.setChecked(true);
                if(mhis.contains(Immuno.getText().toString()))
                    Immuno.setChecked(true);
                if(mhis.contains(Blood.getText().toString()))
                    Blood.setChecked(true);
                if(mhis.contains(Disease.getText().toString()))
                    Disease.setChecked(true);
                if(mhis.contains(BP.getText().toString()))
                    BP.setChecked(true);
                if(mhis.contains(Fibrosis.getText().toString()))
                    Fibrosis.setChecked(true);

                //contact history checkboxes



                if(cont.contains(h1.getText().toString()))
                    h1.setChecked(true);
                if(cont.contains(h2.getText().toString()))
                    h2.setChecked(true);
                if(cont.contains(h3.getText().toString()))
                    h3.setChecked(true);
                if(cont.contains(h4.getText().toString()))
                    h4.setChecked(true);

               // loc= snapshot.child("location").getValue().toString();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(EditProfile.this,Name+Email+ age, Toast.LENGTH_LONG).show();

       /* Cursor res=DB.getdata(Email);
        if(res.getCount()!=0){
            while (res.moveToNext()){
                Name=res.getString(0);
                Contact=res.getString(2);
                age=res.getString(3);
                gender=res.getString(4);
                height=res.getString(5);
                weight=res.getString(6);
                corona=res.getString(7);
                vaccine=res.getString(8);
                medhelp=res.getString(9);
                cont=res.getString(10);
                mhis=res.getString(11);
                loc=res.getString(12);
            }
        }*/

        //Toast.makeText(this, "ola", Toast.LENGTH_LONG).show();

        medsign.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {
                Toast.makeText(EditProfile.this,"Update Clicked",Toast.LENGTH_LONG).show();
                updatedata(ud.getRef());
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });

    }



   @Override
  protected void onStart() {
        super.onStart();
        /*

*/

   }

    public void updatedata(String ref) {

        String pa,cpa,medhelp1=null,gender1=null,Name1=null,Contact1=null, mhis1="*",cont1=null,vaccine1=null,corona1=null,loc1=null,age1=null,weight1=null,height1=null;

        age1=Age.getText().toString();
        weight1=Weight.getText().toString();
        height1=Height.getText().toString();
        Name1=Uname.getText().toString();
        Contact1=Phone.getText().toString();

        pa=Pass.getText().toString();
        cpa=ConPass.getText().toString();

        reff= FirebaseDatabase.getInstance().getReference().child("User_Detail");

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


        if(TextUtils.isEmpty(Name1)){
            Uname.setError("Field required");
            return;
        }



        if(pa!=null){
            if(TextUtils.isEmpty(cpa)){
                ConPass.setError("You need to confirm password");
                return;
            }
            if(pa.length()<6 ){
                Pass.setError("Password size should be greater than or equal to 6 characters");
                return;
            }
            if(cpa.length()<6 || pa.compareTo(cpa)!=0){
                ConPass.setError("Password doesn't match");
                return;
            }
            if(pa.compareTo(cpa)==0){
                user.updatePassword(pa);

            }

        }


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
            Toast.makeText(EditProfile.this,"please select gender",Toast.LENGTH_SHORT).show();
            return ;
        }

        if(M.isChecked()){
            gender1=M.getText().toString();
        }
        if(F.isChecked()){
            gender1=F.getText().toString();
        }
        if(O.isChecked()){
            gender1=O.getText().toString();
        }
        if (cov.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(EditProfile.this,"please select covid status",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(p.isChecked()){
            corona1=p.getText().toString();
        }
        if(neg.isChecked()){
            corona1=neg.getText().toString();
        }
        if (vac.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(EditProfile.this,"please select vaccination status",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(y.isChecked()){
            vaccine1=y.getText().toString();
        }
        if(n.isChecked()){
            vaccine1=n.getText().toString();
        }

        if(Fever.isChecked()){
            medhelp1+=Fever.getText().toString()+",";
        }
        if(Dcough.isChecked()){
            medhelp1+=Dcough.getText().toString()+",";
        }
        if(Tiredness.isChecked()){
            medhelp1+=Tiredness.getText().toString()+",";
        }
        if(Chestp.isChecked()){
            medhelp1+=Chestp.getText().toString()+",";
        }
        if(Sorethroat.isChecked()){
            medhelp1+=Sorethroat.getText().toString()+",";
        }
        if(Diarrhoea.isChecked()){
            medhelp1+=Diarrhoea.getText().toString()+",";
        }
        if(Conjectiv.isChecked()){
            medhelp1+=Conjectiv.getText().toString()+",";
        }
        if(Shortob.isChecked()){
            medhelp1+=Shortob.getText().toString()+",";
        }
        if(Anp.isChecked()){
            medhelp1+=Anp.getText().toString()+",";
        }
        if(Lossos.isChecked()){
            medhelp1+=Lossos.getText().toString()+",";
        }
        if(Preg.isChecked()){
            mhis1+=Preg.getText()+"/";
        }
        if(Allergy.isChecked()){
            mhis1+=Allergy.getText()+"/";
        }
        if(Blood.isChecked()){
            mhis1+=Blood.getText()+"/";
        }
        if(BP.isChecked()){
            mhis1+=BP.getText()+"/";
        }
        if(Immuno.isChecked()){
            mhis1+=Immuno.getText()+"/";
        }
        if(Disease.isChecked()){
            mhis1+=Disease.getText()+"/";
        }
        if(Fibrosis.isChecked()){
            mhis1+=Fibrosis.getText()+"/";
        }
        if(h1.isChecked()){
            cont1+=h1.getText().toString();
        }
        if(h2.isChecked()){
            cont1+=h2.getText().toString();
        }
        if(h3.isChecked()){
            cont1+=h3.getText().toString();
        }
        if(h4.isChecked()){
            cont1+=h4.getText().toString();
        }
        HashMap hm=new HashMap();
        hm.put("username",Name1);
        hm.put("age",age1);
        hm.put("contact",Contact1);
        hm.put("contactHistory",cont1);
       // hm.put("email",Email);
        hm.put("gender",gender1);
        hm.put("healthCondition",mhis1);
        hm.put("height",height1);
        hm.put("symptoms",medhelp1);
        hm.put("vaccinationStatus",vaccine1);
        hm.put("weight",weight1);
        hm.put("coronaStatus",corona1);
        /*
        User_Detail ud=new User_Detail();
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
*/
        reff.child(ref).updateChildren(hm).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(EditProfile.this,"Update Successful",Toast.LENGTH_LONG);
            }
        });
        //ud.setLat(lat);
        //ud.setLon(lon);
        //ud.setState(state);



        //boolean x=DB.updateuserdata(Name1,Email,Contact1,age1,gender1,height1,weight1,corona1,vaccine1,medhelp1,mhis1,cont1,loc);
      /*  if(x==true)
        {
            Toast.makeText(EditProfile.this,"Signed in successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), Menu.class));
        }
        else
            Toast.makeText(EditProfile.this,loc+"Error",Toast.LENGTH_LONG).show();*/

    }
}