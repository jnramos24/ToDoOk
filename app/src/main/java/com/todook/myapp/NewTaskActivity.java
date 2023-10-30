package com.todook.myapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.todook.myapp.db.DbHelper;

public class NewTaskActivity extends AppCompatActivity {
    private Button saveButton;
    private SQLiteDatabase db;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        ImageView img_back = findViewById(R.id.img_back_c);
        saveButton = findViewById(R.id.saveButton);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentback = new Intent(NewTaskActivity.this, MainActivity.class);
                startActivity(intentback);
            }
        });

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        DbHelper usdbh =    new DbHelper(this);

        db = usdbh.getWritableDatabase();
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String taskName = ((EditText) findViewById(R.id.edittitle)).getText().toString();
                String taskDate = ((EditText) findViewById(R.id.editdate)).getText().toString();
                String taskTime = ((EditText) findViewById(R.id.edittime)).getText().toString();
                int priority = getPriorityFromRadioGroup(); // Implementa el método para obtener la prioridad del radio group

                //método insert()
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("taskname", taskName);
                nuevoRegistro.put("taskdate", taskDate);
                nuevoRegistro.put("timedate", taskTime);
                nuevoRegistro.put("type", priority);

                long resultado = db.insert("task", null, nuevoRegistro);

                if (resultado != -1) {
                    // La inserción fue exitosa
                    Toast.makeText(NewTaskActivity.this, "Tarea agregada a la base de datos", Toast.LENGTH_SHORT).show();
                    // Limpiar los campos de texto
                    ((EditText) findViewById(R.id.edittitle)).setText("");
                    ((EditText) findViewById(R.id.editdate)).setText("");
                    ((EditText) findViewById(R.id.edittime)).setText("");
                    // Limpiar el RadioGroup
                    RadioGroup radioGroup = findViewById(R.id.radioGroup);
                    radioGroup.clearCheck();
                } else {
                    // Hubo un error al agregar la tarea a la base de datos
                    Toast.makeText(NewTaskActivity.this, "Error al agregar la tarea a la base de datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private int getPriorityFromRadioGroup() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        int priority = 0;
        if (selectedRadioButtonId == R.id.radButton1) {
            priority = 1;
        } else if (selectedRadioButtonId == R.id.radButton2) {
            priority = 2;
        } else if (selectedRadioButtonId == R.id.radButton3) {
            priority = 3;
        }

        return priority;
    }
}

