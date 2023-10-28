package com.todook.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.miActionBar);
        setSupportActionBar(toolbar);

       /* Button btnContact = findViewById(R.id.btn_contact);
        Button btnPerfil = findViewById(R.id.btn_perfil);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });*/


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
}