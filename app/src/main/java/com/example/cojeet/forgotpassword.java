package com.example.cojeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    private EditText regemail1;
    private Button regbutton1;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        regemail1=findViewById(R.id.regemail);
        regbutton1=findViewById(R.id.regbutton);
        auth=FirebaseAuth.getInstance();

        regbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
    private void resetpassword() {
        String email=regemail1.getText().toString();

        if(email.isEmpty()){
            regemail1.setError("This field is required");
            regemail1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            regemail1.setError("Provide valid email address");
            regemail1.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgotpassword.this,"Check your email address for password reset",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(forgotpassword.this,"Something went wrong, Try again.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}