package com.example.proyandroid;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VistaParticipantesAdapter extends RecyclerView.Adapter<VistaParticipantesAdapter.MyViewHolder> {

        Context c;
        ArrayList<Participante> participantes;
        int [] imagenes = {R.drawable.toystory1,R.drawable.toystory2,R.drawable.toystory3,R.drawable.toystory4,R.drawable.toystory5,
                R.drawable.toystory6,R.drawable.toystory7,R.drawable.toystory8,R.drawable.toystory9,R.drawable.toystory10,
                R.drawable.toystory11,R.drawable.toystory12,R.drawable.toystory13,R.drawable.toystory1,R.drawable.toystory2,R.drawable.toystory3};

        public VistaParticipantesAdapter (Context context) {
            c = context;
            participantes = new ArrayList<>();
            participantes = MainActivity.viewModel.getParticipantes();
        }

        @NonNull
        @Override
        public VistaParticipantesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v;
            v = LayoutInflater.from(c).inflate(R.layout.layout_participantes,parent,false);
            MyViewHolder holder = new MyViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

            holder.txt1.setText(participantes.get(position).getNombre());
            holder.img.setImageResource(imagenes[position]);
            if(participantes.get(position).getEstado()==0) {
                holder.imgCheck.setImageResource(R.drawable.ic_baseline_check_24);
            }
            else
            {
                holder.imgCheck.setImageResource(R.drawable.ic_baseline_check_24_green);
            }
            holder.imgCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.imgCheck.setImageResource(R.drawable.ic_baseline_check_24_green);
                    MainActivity.viewModel.pagarByParticipante(position+1);
                    if(MainActivity.viewModel.totalPagados()==getItemCount())
                    {
                        MainActivity.viewModel.reiniciarPagos();
                    }
                }
            });

        }


        @Override
        public int getItemCount() {
            return participantes.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder{
            private TextView txt1;
            private ImageView img,imgCheck;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                txt1 = (TextView) itemView.findViewById(R.id.txtParticipante);
                img = (ImageView) itemView.findViewById(R.id.imgParticipante);
                imgCheck = (ImageView) itemView.findViewById(R.id.imgCheck);

            }
        }
    }


