package com.example.cojeet.hospitals.hospitalmodels;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cojeet.Covidnews.Detailed;
import com.example.cojeet.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static java.lang.Math.abs;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<String> hospitalname,hospital_loc,contact,facility;
    private List<Double>latitude_geo,longitude_geo;
    private LayoutInflater inflater;
    Double latitude,longitude;
    Context context;

    public Adapter(Context context, List<String> hospitalname, List<String> hospital_loc, List<String> contact, List<String> facility,List<Double>latitude_geo,List<Double>longitude_geo,Double latitude,Double longitude){
        this.context = context;
        this.hospitalname = hospitalname;
        this.hospital_loc = hospital_loc;
        this.contact = contact;
        this.facility = facility;
        this.latitude_geo=latitude_geo;
        this.longitude_geo=longitude_geo;
        this.inflater = LayoutInflater.from(context);
        this.latitude=latitude;
        this.longitude=longitude;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hospitalitems,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String hname = hospitalname.get(position);
        String address = hospital_loc.get(position);
        String contact1 = contact.get(position);
        String facility1 = facility.get(position);
        Double pos_lat=latitude_geo.get(position);
        Double pos_long=longitude_geo.get(position);
        //if(abs(Double.parseDouble(pos_lat)-Double.parseDouble(latitude))<=0.1 && abs(Double.parseDouble(pos_long)-Double.parseDouble(longitude))<=0.1)
        holder.hname1.setText(hname);
        holder.desc.setText(facility1);
        holder.contactfinal.setText(contact1);
        holder.locality.setText(address);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("locations", latitude.toString()+" "+longitude.toString()+" "+latitude_geo.get(position).toString()+" "+longitude_geo.get(position).toString());
                Intent intent = new Intent(context, HospitalDetailed.class);
                intent.putExtra("user_lat",latitude);
                intent.putExtra("user_lon",longitude);
                intent.putExtra("lat",pos_lat);
                intent.putExtra("lon",pos_long);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hospitalname.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hname1,desc,contactfinal,locality;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hname1 = itemView.findViewById(R.id.hname);
            desc = itemView.findViewById(R.id.facility);
            contactfinal = itemView.findViewById(R.id.contact1);
            locality = itemView.findViewById(R.id.locality);
            cardView=itemView.findViewById(R.id.cardView1);

        }
    }
}