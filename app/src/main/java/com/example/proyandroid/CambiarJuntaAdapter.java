package com.example.proyandroid;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CambiarJuntaAdapter extends RecyclerView.Adapter<CambiarJuntaAdapter.MyViewHolder>{
    List<String> participantesCambio = new ArrayList<>();

    public CambiarJuntaAdapter() {

    }

    @NonNull
    @Override
    public CambiarJuntaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cambiarparticipante_layout,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CambiarJuntaAdapter.MyViewHolder holder, int position) {

        holder.txt1.setText(String.valueOf(position+1));
        holder.txt2.setText(participantesCambio.get(position));
    }

    public void setList(List <String> nombres)
    {
        participantesCambio = nombres;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return participantesCambio.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txt1,txt2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt1 = (TextView) itemView.findViewById(R.id.txtPos);
            txt2 = (TextView) itemView.findViewById(R.id.txtNom);

        }
    }

}
