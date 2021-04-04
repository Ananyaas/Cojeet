package com.example.cojeet.Vaccinationstats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import com.example.cojeet.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.util.ArrayList;

public class VaccinationStatistics extends AppCompatActivity {
    DatabaseReference ref;

    statlist sl=new statlist();
  RecyclerView recyclerView;
     RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_statistics);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(VaccinationStatistics.this);
        //recyclerView.setLayoutManager(layoutManager);
        ref= FirebaseDatabase.getInstance().getReference().child("User_Detail");
        Query query=ref.orderByChild("state");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                sl.init();
                String s= snapshot.getValue().toString();
                String[] s1=s.split("-");
                for(int i=1;i<s1.length;i++){
                    if(s1[i].contains("vaccinationStatus=Vaccinated")){
                        sl.incr_v_count(s1[i]);
                    }
                }
                for(int i=1;i<s1.length;i++){
                    if(s1[i].contains("/")){
                        sl.incr_h_count(s1[i]);
                    }
                }

              //  for(String ss:s1){
                //    Log.e("onDataChange: ",ss );
                //}
            //   for (s_data sd : sl.getArr())
              //      Log.e("stst", sd.getState() + " " + sd.getV_count() + " " + sd.getH_count());

               // recyclerView=findViewById(R.id.recyclerView);
               // recyclerView.setHasFixedSize(true);
                //layoutManager=new LinearLayoutManager(VaccinationStatistics.this);
               adapter=new ExampleAdapter(sl.getArr());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


      //  adapter=new ExampleAdapter(sl.getArr());


    }
}
