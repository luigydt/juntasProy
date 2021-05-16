package com.example.proyandroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyandroid.MainActivity;
import com.example.proyandroid.R;

public class CrearSorteoFragment extends Fragment implements View.OnClickListener {
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crear_sorteo,container,false);
        imageView = view.findViewById(R.id.imgDados);
        imageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        if(MainActivity.viewModel.countParticipantes()>0) {
            if (MainActivity.viewModel.countParticipantes() < MainActivity.viewModel.getParticipantesJunta()) {
                Toast.makeText(getContext(), "Faltan Participantes por añadir", Toast.LENGTH_SHORT).show();
            }
            else
                {
                    if(MainActivity.viewModel.countSorteo()>1)
                    {
                        Toast.makeText(getContext(), "No puedes reinicar Sorteo hasta Nueva Junta", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        MainActivity.viewModel.crearSorteo();
                        Toast.makeText(getContext(), "Sorteo Creado", Toast.LENGTH_SHORT).show();
                    }
            }
        }
        else
        {

            Toast.makeText(getContext(), "No has creado una Junta", Toast.LENGTH_SHORT).show();
        }

    }
}
