package com.example.proyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.proyandroid.Fragments.AnadirParticipanteFragment;
import com.example.proyandroid.Fragments.CrearJuntaFragment;
import com.example.proyandroid.Fragments.CrearSorteoFragment;
import com.example.proyandroid.Fragments.FragmentCambiarSorteo;
import com.example.proyandroid.Fragments.FragmentVistaParticipantes;
import com.example.proyandroid.Fragments.VerJuntaFragment;
import com.example.proyandroid.Fragments.MainFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    //variable para fragment principal
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    public static ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModel(this);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerMain);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        //cargar fragment principal
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,new MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.home)
        {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new CrearJuntaFragment());
            fragmentTransaction.commit();
        }
        else if(item.getItemId() == R.id.verJunta)
        {
            if(viewModel.countSorteo()==0)
            {
                Toast.makeText(this,"Aun no has creado Junta",Toast.LENGTH_SHORT).show();
            }
            else {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new VerJuntaFragment());
                fragmentTransaction.commit();
            }
        }
        else if(item.getItemId() == R.id.crearPart)
        {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new AnadirParticipanteFragment());
            fragmentTransaction.commit();
        }
        else if(item.getItemId()==R.id.verParticipantes)
        {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new FragmentVistaParticipantes());
            fragmentTransaction.commit();
        }
        else if(item.getItemId()==R.id.crearSorteo)
        {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new CrearSorteoFragment());
            fragmentTransaction.commit();
        }
        else if(item.getItemId()==R.id.modificarPart)
        {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new FragmentCambiarSorteo());
            fragmentTransaction.commit();
        }

        return false;
    }


}