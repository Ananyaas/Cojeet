package com.example.cojeet.Covidstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cojeet.Covidstats.covidstatsapi.RegionDatum;
import com.example.cojeet.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


      Context context;
     List<RegionDatum> regionData;

    public Adapter(Context context, List<RegionDatum> regionData) {
        this.context = context;
        this.regionData = regionData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coviditems,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final RegionDatum a=regionData.get(position);
        holder.state.setText(a.getRegion());
        holder.totalactive.setText(Integer.toString(a.getTotalInfected()));
        holder.totalrecover.setText(Integer.toString(a.getRecovered()));
        holder.totaldeath.setText(Integer.toString(a.getDeceased()));
        holder.tnewinf.setText(Integer.toString(a.getNewInfected()));
        holder.tnewrec.setText(Integer.toString(a.getNewRecovered()));
        holder.tnewdec.setText(Integer.toString(a.getNewDeceased()));


    }

    @Override
    public int getItemCount() {
        return regionData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView state,totalactive,totalrecover,totaldeath,tnewinf,tnewrec,tnewdec;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            state=itemView.findViewById(R.id.statetitle);
            totalactive=itemView.findViewById(R.id.totalInfected1);
            totalrecover=itemView.findViewById(R.id.recovered1);
            totaldeath=itemView.findViewById(R.id.deceased1);
            tnewinf=itemView.findViewById(R.id.newinf1);
            tnewrec=itemView.findViewById(R.id.newrec1);
            tnewdec=itemView.findViewById(R.id.newdec1);
        }
    }
}
