package com.todook.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.todook.myapp.modelo.Task;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Task> tasks;
    private RecyclerView rvTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabButton = findViewById(R.id.fab);

        Toolbar toolbar = findViewById(R.id.miActionBar);
        setSupportActionBar(toolbar);

        rvTasks =  findViewById(R.id.rvTasks);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvTasks.setLayoutManager(llm);
        inicializarListaTasks();
        inicializarAdaptador();

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentnew = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivity(intentnew);
            }
        });


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

    public void inicializarListaTasks(){
        tasks = new ArrayList<Task>();
        tasks.add(new Task(1,1,"Tarea 1","12/11/2023","12:00", 1));
        tasks.add(new Task(2,1,"Tarea 2","10/12/2023","09:00", 1));
        tasks.add(new Task(3,1,"Tarea 3","05/11/2023","18:00", 2));
        tasks.add(new Task(4,1,"Tarea 4","25/12/2023","08:00", 3));
        tasks.add(new Task(5,1,"Tarea 4","01/11/2023","18:00", 1));
        tasks.add(new Task(6,1,"Tarea 6","01/01/2024","10:00", 2));
        tasks.add(new Task(7,1,"Tarea 7","30/12/2023","21:00", 3));
    }
}