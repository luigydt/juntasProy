package com.example.proyandroid.Fragments;

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

import com.example.proyandroid.MainActivity;
import com.example.proyandroid.VerJuntaAdapter;
import com.example.proyandroid.R;

public class VerJuntaFragment extends Fragment  {

    private RecyclerView mV;
    TextView txtHide;
    VerJuntaAdapter pA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ver_junta,container,false);
        mV = view.findViewById(R.id.reciclerParticipantes);
        pA = new VerJuntaAdapter(getContext());
        mV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mV.setAdapter(pA);
        txtHide = view.findViewById(R.id.txtHide);
        String cantidad ="Cantidad: " + MainActivity.viewModel.getCantidad() + "€";
        txtHide.setText(cantidad);


        return view;

    }




}
