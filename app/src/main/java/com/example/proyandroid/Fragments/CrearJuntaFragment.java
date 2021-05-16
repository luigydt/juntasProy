package com.example.proyandroid.Fragments;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyandroid.MainActivity;
import com.example.proyandroid.R;

import java.util.Calendar;

public class CrearJuntaFragment extends Fragment implements View.OnClickListener {

    Button btnCrear;
    ImageView imgCalendar;
    EditText txt_nPersonas,txtCantidad,txtFecha;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.crearjunta_layout,container,false);
        btnCrear = view.findViewById(R.id.btnCrear);
        imgCalendar = view.findViewById(R.id.imgCalendar);
        txt_nPersonas = view.findViewById(R.id.txtPersonas);
        txtCantidad = view.findViewById(R.id.txtCantidad);
        txtFecha = view.findViewById(R.id.txtFecha);

        btnCrear.setOnClickListener(this);
        imgCalendar.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        if(v==btnCrear) {

            String nPersonas = txt_nPersonas.getText().toString();
            String cantidad = txtCantidad.getText().toString();
            MainActivity.viewModel.crearJunta(Integer.parseInt(nPersonas), Integer.parseInt(cantidad),txtFecha.getText().toString());
            Toast.makeText(getContext(), "Junta Añadida", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Calendar calendar = Calendar.getInstance();
            final int anio = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    String fecha = year+"-"+month+"-"+dayOfMonth;
                    txtFecha.setText(fecha);

                }
            },anio,mes,dia);
            dpd.show();
        }

    }
}