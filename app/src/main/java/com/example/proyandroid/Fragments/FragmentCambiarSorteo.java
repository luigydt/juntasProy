package com.example.proyandroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyandroid.CambiarJuntaAdapter;
import com.example.proyandroid.MainActivity;
import com.example.proyandroid.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FragmentCambiarSorteo extends Fragment implements View.OnClickListener {
    RecyclerView rv;
    CambiarJuntaAdapter pA;
    List<String> dataSetSorteo = new ArrayList<>();
    String [] nombres = MainActivity.viewModel.nombresSorteo();
    Button btnActualizar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        for(int i=0;i<nombres.length;i++)
        {
            dataSetSorteo.add(nombres[i]);
        }

        View view = inflater.inflate(R.layout.fragment_cambiar_sorteo,container,false);

        rv = view.findViewById(R.id.recyclerSorteo);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        pA = new CambiarJuntaAdapter();
        pA.setList(dataSetSorteo);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        btnActualizar = view.findViewById(R.id.btnCambiar);
        btnActualizar.setOnClickListener(this);

        rv.setAdapter(pA);

        return view;


    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int pos_dragged = viewHolder.getAdapterPosition();
            int pos_target = target.getAdapterPosition();
            Collections.swap(dataSetSorteo, pos_dragged,pos_target );
            recyclerView.getAdapter().notifyItemMoved(pos_dragged, pos_target);

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        }

        @Override
        public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y) {
            super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);

        }
    };

    @Override
    public void onClick(View v) {

        MainActivity.viewModel.actualizarSorteo(dataSetSorteo);
        Toast.makeText(getContext(), "Sorteo Cambiado", Toast.LENGTH_SHORT).show();
    }
}
