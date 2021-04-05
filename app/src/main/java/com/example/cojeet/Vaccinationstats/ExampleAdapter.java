package com.example.cojeet.Vaccinationstats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cojeet.R;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<s_data> examplelist;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView state;
        public TextView vrno;
        public TextView hrno;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            state=itemView.findViewById(R.id.vState);
            vrno=itemView.findViewById(R.id.vnoval);
            hrno=itemView.findViewById(R.id.hrval);
        }
    }

    public ExampleAdapter(ArrayList<s_data> el){
        examplelist=el;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.vac_stat,parent,false);
        ExampleViewHolder evh=new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        s_data cur=examplelist.get(position);

        holder.state.setText(cur.getState());
        holder.vrno.setText(""+cur.getV_count());
        holder.hrno.setText(""+cur.getH_count());
    }

    @Override
    public int getItemCount() {
        return examplelist.size();
    }
}
