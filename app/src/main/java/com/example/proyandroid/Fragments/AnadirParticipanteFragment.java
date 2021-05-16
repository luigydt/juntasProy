package com.example.proyandroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyandroid.MainActivity;
import com.example.proyandroid.R;

public class AnadirParticipanteFragment extends Fragment implements View.OnClickListener {
    Button btnAnadir;
    EditText txtNombre;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_anadirparticipante,container,false);
        btnAnadir = view.findViewById(R.id.btnAnadir);
        txtNombre = view.findViewById(R.id.txtNombre);
        btnAnadir.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(MainActivity.viewModel.countJuntas()>0) {
            int nPersonas = MainActivity.viewModel.getParticipantesJunta();
            if (MainActivity.viewModel.countSorteo() != 0) {
                if (MainActivity.viewModel.countParticipantes() >= nPersonas) {
                    Toast.makeText(getContext(), "No puedes añadir mas participantes a esta Junta", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        MainActivity.viewModel.crearParticipante(txtNombre.getText().toString());
                        Toast.makeText(getContext(), "Se añadio " + txtNombre.getText().toString() + " a la lista", Toast.LENGTH_SHORT).show();
                        txtNombre.setText("");
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "No se pudo añadir", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(getContext(), "Debes crear una Junta Primero", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getContext(), "Debes crear una Junta Primero", Toast.LENGTH_SHORT).show();
        }
    }
}
