package com.todook.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.todook.myapp.db.ConstantesBaseDatos;
import com.todook.myapp.db.DbHelper;
import com.todook.myapp.modelo.Task;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Task> tasks;
    private RecyclerView rvTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // Si el usuario no ha iniciado sesión, redirigir a LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Usuario ha iniciado sesión correctamente, permitir acceso a MainActivity

            setContentView(R.layout.activity_main);

            FloatingActionButton fabButton = findViewById(R.id.fab);
            Toolbar toolbar = findViewById(R.id.miActionBar);
            setSupportActionBar(toolbar);

            rvTasks = findViewById(R.id.rvTasks);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            rvTasks.setLayoutManager(llm);
            cargarListaTasks();
            inicializarAdaptador();

            fabButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentnew = new Intent(MainActivity.this, NewTaskActivity.class);
                    startActivity(intentnew);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.mPerfil) {
            // Lógica para la opción de perfil
            Intent intent =new Intent(this,Profile.class);
            startActivity(intent);
        } else if (id == R.id.mContacto) {
            // Lógica para la opción de contacto
            Intent i =new Intent(this,ContactActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



    public TaskAdapter adaptador;
    public void inicializarAdaptador(){
        adaptador = new TaskAdapter(tasks, this);
        rvTasks.setAdapter(adaptador);
    }

    public void cargarListaTasks() {
        DbHelper dbHelper = new DbHelper(this);
        tasks = dbHelper.obtenerTodasLasTareas();
    }

    
    // Funcion para cambio de temas
    public void setDayNight(int mode) {
        if (mode == 0) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}