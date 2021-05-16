package com.example.proyandroid;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VerJuntaAdapter extends RecyclerView.Adapter<VerJuntaAdapter.MyViewHolder> {

    Context c;
    ArrayList <Participante> participantesJunta;
    String [] meses = {"FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE","ENERO","FEBRERO"};



    public VerJuntaAdapter(Context context) {
        c = context;
        participantesJunta = new ArrayList<>();
        participantesJunta = MainActivity.viewModel.getParticipantesSorteo();
    }

    @NonNull
    @Override
    public VerJuntaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.participante_layout,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VerJuntaAdapter.MyViewHolder holder, final int position) {


        holder.txtMes.setText(meses[position]);
        holder.txt1.setText(participantesJunta.get(position).getNombre());
        if(participantesJunta.get(position).getEstado()==1)
        {
            holder.imgPagado.setImageResource(R.drawable.billeteicon_pagado);
            holder.txtMes.setTextColor(Color.GREEN);
        }
        else
        {
            holder.imgPagado.setImageResource(R.drawable.billeteicon);
        }


        holder.imgPagado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.imgPagado.setImageResource(R.drawable.billeteicon_pagado);
                holder.txtMes.setTextColor(Color.GREEN);
                MainActivity.viewModel.pagarJunta(position+1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return participantesJunta.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txt1,txtMes;
        private ImageView imgPagado;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt1 = (TextView) itemView.findViewById(R.id.txtNombre1);
            imgPagado = (ImageView) itemView.findViewById(R.id.imgPagado);
            txtMes = (TextView) itemView.findViewById(R.id.txtMes);
        }
    }

}





