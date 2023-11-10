package com.todook.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SetProfileActivity extends AppCompatActivity {

    ImageView imagback;
    Button btnGuardar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);

        imagback = findViewById(R.id.img_back_sp);

        imagback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetProfileActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        btnGuardar = findViewById(R.id.saveButton2);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(SetProfileActivity.this, "Guardado Correctamente", Toast.LENGTH_SHORT).show();

        }
    });

    }
}