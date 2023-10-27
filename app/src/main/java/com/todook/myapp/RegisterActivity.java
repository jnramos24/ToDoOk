package com.todook.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.todook.myapp.db.DbUsers;

public class RegisterActivity extends AppCompatActivity {

    ImageView iconVolver;
    EditText txtName, txtEmail, txtPassword;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iconVolver = findViewById(R.id.icon_volver_R);

        iconVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        txtName = findViewById(R.id.editTextUsername_R);
        txtEmail = findViewById(R.id.editTextEmail_R);
        txtPassword = findViewById(R.id.editTextPassword_R);
        btnRegister = findViewById(R.id.buttonRegister_R);

        btnRegister.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              DbUsers dbUsers = new DbUsers(RegisterActivity.this);
              long id = dbUsers.insertUser(txtName.getText().toString(), txtEmail.getText().toString(), txtPassword.getText().toString());
              
              if(id > 0){
                  Toast.makeText(RegisterActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                  limpiar();
              } else {
                  Toast.makeText(RegisterActivity.this, "ERROR AL GUARDAR", Toast.LENGTH_SHORT).show();
              }
          }
      } );

        TextView linkLogin= findViewById(R.id.textIniciarSesion_R);
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void limpiar(){
        txtName.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
    }
}