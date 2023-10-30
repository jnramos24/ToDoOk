package com.todook.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

                    // Credenciales válidas, almacenar el estado de la sesión
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                    //redirige a la MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Credenciales inválidas, muestra mensaje de error
                    Toast.makeText(LoginActivity.this, "Email y/o contraseña inválido", Toast.LENGTH_SHORT).show();
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