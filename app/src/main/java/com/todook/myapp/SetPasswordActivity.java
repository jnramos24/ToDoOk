package com.todook.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SetPasswordActivity extends AppCompatActivity {

    ImageView imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Crea un Intent para abrir la OtraActividad
                Intent intent = new Intent(SetPasswordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imgback = findViewById(R.id.img_back_sp);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetPasswordActivity.this, Profile.class);
                startActivity(intent);
            }
        });



    }
}