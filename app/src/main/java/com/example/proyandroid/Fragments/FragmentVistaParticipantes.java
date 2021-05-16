package com.example.proyandroid.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyandroid.R;
import com.example.proyandroid.VistaParticipantesAdapter;

import java.util.Calendar;

public class FragmentVistaParticipantes extends Fragment {
    RecyclerView recyclerView;
    TextView mes;
    String  [] meses =  {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
    Calendar c;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_verparticipantes,container,false);
        c = Calendar.getInstance();
        recyclerView = view.findViewById(R.id.rVParticipantes);
        mes = view.findViewById(R.id.txtMes);
        mes.setText(meses[c.get(Calendar.MONTH)]);
        mes.setTextColor(Color.CYAN);
        VistaParticipantesAdapter  vpa = new VistaParticipantesAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(vpa);
        return view;

    }

}
