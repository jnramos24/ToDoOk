package com.todook.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.todook.myapp.db.DbUsers;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.buttonLogin);
        TextView linkRegister= findViewById(R.id.textView5);

        EditText editTextUsername = findViewById(R.id.editTextUsername_L);
        EditText editTextPassword = findViewById(R.id.editTextPassword_L);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Verificamos mail y contraseña
                DbUsers dbUsers = new DbUsers(LoginActivity.this);
                boolean isValidCredentials = dbUsers.checkCredentials(email, password);

                if (isValidCredentials) {
                    // Credenciales válidas, redirige a la MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // Credenciales inválidas, muestra mensaje de error
                    Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}