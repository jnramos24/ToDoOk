package com.todook.myapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.todook.myapp.db.DbHelper;

public class Profile extends AppCompatActivity {

    ImageView imgback;
    Button btnActualize;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgback = findViewById(R.id.icon_volver_R);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnActualize = findViewById(R.id.btnActualize);
        btnActualize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper DbHelper = new DbHelper(Profile.this);
                SQLiteDatabase db = DbHelper.getWritableDatabase();
                if(db != null){
                        Toast.makeText(Profile.this, "Base de datos Creada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Profile.this, "Error al crear Base de datos ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
