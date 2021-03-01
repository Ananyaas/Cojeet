package com.example.cojeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Signup2 extends AppCompatActivity {
    EditText Age,Height,Weight,Corona,Vaccine;
    RadioButton M,F;
    Button medsign;
    DBHelper DB;
    RadioGroup rb;
    String medhelp,gender,Email;
    CheckBox Fever,Dcough,Chestp,Tiredness,Diarrhoea,Conjectiv,Shortob,Anp,Lossos,Sorethroat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        Intent intent = getIntent();
        Email= intent.getStringExtra ("Email");

        Age=findViewById(R.id.ed1);
        Weight=findViewById(R.id.ed2);
        Vaccine=findViewById(R.id.ed3);
        Height=findViewById(R.id.ed4);
        Corona=findViewById(R.id.ed5);

        M=findViewById(R.id.rb11);
        F=findViewById(R.id.rb12);
        rb=findViewById(R.id.rb1);
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
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    private void storedata() {
        String age=Age.getText().toString();
        String weight=Weight.getText().toString();
        String height=Height.getText().toString();
        String vaccine=Vaccine.getText().toString();
        String corona=Corona.getText().toString();

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
        if(TextUtils.isEmpty(vaccine)){
            Vaccine.setError("Field required");
            return;
        }
        if(TextUtils.isEmpty(corona)){
            Corona.setError("Field required");
            return;
        }
        if (rb.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(Signup2.this,"please select gender",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(M.isSelected()){
            gender="male";
        }
        if(F.isSelected()){
            gender="female";
        }
        if(Fever.isChecked()){
            medhelp=medhelp+", "+"fever";
        }
        if(Dcough.isChecked()){
            medhelp=medhelp+", "+"Dry Cough";
        }
        if(Tiredness.isChecked()){
            medhelp=medhelp+", "+"Tiredness";
        }
        if(Chestp.isChecked()){
            medhelp=medhelp+", "+"Chest Pain";
        }
        if(Sorethroat.isChecked()){
            medhelp=medhelp+", "+"Sorethroat";
        }
        if(Diarrhoea.isChecked()){
            medhelp=medhelp+", "+"Diarrhoea";
        }
        if(Conjectiv.isChecked()){
            medhelp=medhelp+", "+"Conjunctivitis";
        }
        if(Shortob.isChecked()){
            medhelp=medhelp+", "+"Short Of Breath";
        }
        if(Anp.isChecked()){
            medhelp=medhelp+", "+"Aches and Pains";
        }
        if(Lossos.isChecked()){
            medhelp=medhelp+", "+"Loss Of speech and movement";
        }

        boolean x=DB.insertData2(age,gender,height,weight,corona,vaccine,medhelp);
        if(x==true)
        {
            Toast.makeText(Signup2.this,"Signed in successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
        }
        else
            Toast.makeText(Signup2.this,"Something went wrong",Toast.LENGTH_LONG).show();
    }
}