package com.todook.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.todook.myapp.modelo.Task;

import com.todook.myapp.db.DbHelper;

import java.util.ArrayList;

public class TaskEditActivity extends AppCompatActivity {


    private DbHelper dbHelper;
    private TaskAdapter taskAdapter;
    private RecyclerView tuRecyclerView;


    private ImageView iconBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);


        // Inicializar dbHelper
        dbHelper = new DbHelper(this);


        // Infla el layout que contiene el RecyclerView
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        tuRecyclerView = rootView.findViewById(R.id.rvTasks);


        // Configura el adaptador y cualquier otra configuración necesaria para tu RecyclerView
        final TaskAdapter taskAdapter = new TaskAdapter(dbHelper.obtenerTodasLasTareas(), this);
        // Usa la variable de instancia:
        tuRecyclerView.setAdapter(taskAdapter);


        // Obtener datos de Intent
        Intent intent = getIntent();
        int taskId = intent.getIntExtra("taskId", 0);
        String taskName = intent.getStringExtra("taskName");
        String taskDate = intent.getStringExtra("taskDate");
        String timeDate = intent.getStringExtra("timeDate");
        int taskType = intent.getIntExtra("taskType", 1); // Ajusta el valor predeterminado según lógica


        // Vincular vistas
        EditText editTitle = findViewById(R.id.editTitle);
        EditText editFecha = findViewById(R.id.editFecha);
        EditText editTime = findViewById(R.id.editTime);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);


        // Mostrar datos en vistas
        editTitle.setText(taskName);
        editFecha.setText(taskDate);
        editTime.setText(timeDate);


        // Seleccionar el botón de radio correspondiente
        switch (taskType) {
            case 1:
                radioGroup.check(R.id.radButton1);
                break;
            case 2:
                radioGroup.check(R.id.radButton2);
                break;
            case 3:
                radioGroup.check(R.id.radButton3);
                break;
            // Ajusta según sea necesario
        }


        Button btnActualizar = findViewById(R.id.button);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén los datos editados
                String editedTitle = editTitle.getText().toString();
                String editedFecha = editFecha.getText().toString();
                String editedTime = editTime.getText().toString();


                // Obtén la prioridad seleccionada
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                int editedPriority = 1; // Valor predeterminado, ajusta según sea necesario


                if (selectedRadioButtonId == R.id.radButton1) {
                    editedPriority = 1;
                } else if (selectedRadioButtonId == R.id.radButton2) {
                    editedPriority = 2;
                } else if (selectedRadioButtonId == R.id.radButton3) {
                    editedPriority = 3;
                }


                // Actualizar los datos en la base de datos usando
                // método en DbHelper para actualizar la tarea
                // se usa el ID de la tarea para identificarla
                dbHelper.actualizarTarea(taskId, editedTitle, editedFecha, editedTime, editedPriority);


                // Actualiza el RecyclerView
                // Después de actualizar la base de datos
                ArrayList<Task> nuevasTareas = dbHelper.obtenerTodasLasTareas();
                if (taskAdapter != null) {
                    taskAdapter.actualizarTareas(nuevasTareas);
                    Toast.makeText(TaskEditActivity.this, "Tarea Actualizada", Toast.LENGTH_SHORT).show();


                }


                // retroceder a la actividad main después de actualizar


                Intent intent = new Intent(TaskEditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Button btnCancel = findViewById(R.id.button2);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Si el usuario hace clic en "Cancelar", simplemente cierra la actividad
                finish();
            }
        });


        iconBack = findViewById(R.id.img_back);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskEditActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });


    }




}

