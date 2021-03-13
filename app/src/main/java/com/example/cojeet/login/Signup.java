package com.example.cojeet.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.cojeet.Menu;

import com.example.cojeet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText name,email,pwd,conpwd,contact1;
    Button signup1;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.username);
        email=findViewById(R.id.signupemail);
        pwd=findViewById(R.id.signuppassword);
        conpwd=findViewById(R.id.signupconfpass);
        contact1=findViewById(R.id.contact);
        signup1=findViewById(R.id.signupbutton);
        auth=FirebaseAuth.getInstance();


       if(auth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), Menu.class));
        


        signup1.setOnClickListener(new View.OnClickListener() {
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
        String name2=name.getText().toString();
        String email2=email.getText().toString();
        String pwd2=pwd.getText().toString();
        String conpwd2=conpwd.getText().toString();
        String contact2=contact1.getText().toString();

        if(TextUtils.isEmpty(name2)){
            name.setError("Field required");
            return;
        }
        if(TextUtils.isEmpty(email2)){
            email.setError("Field required");
            return;
        }
        if(TextUtils.isEmpty(pwd2)){
            pwd.setError("Field required");
            return;
        }
        if(TextUtils.isEmpty(conpwd2)){
            conpwd.setError("Field required");
            return;
        }
        if(TextUtils.isEmpty(contact2)){
            contact1.setError("Field required");
            return;
        }
        if(pwd2.length()<6 ){
            pwd.setError("Password size should be greater than or equal to 6 characters");
            return;
        }
        if(conpwd2.length()<6 || pwd2.compareTo(conpwd2)!=0){
            conpwd.setError("Password doesn't match");
            return;
        }
        auth.createUserWithEmailAndPassword(email2,pwd2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(Signup.this,"Welcome user",Toast.LENGTH_LONG).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", name2);
                    bundle.putString("Email", email2);
                    bundle.putString("Contact", contact2);
                    Intent intent=new Intent(Signup.this, Signup2.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Signup.this,"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}