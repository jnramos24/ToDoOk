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
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (isValidEmail(email) && isValidPassword(password)) {
                    DbUsers dbUsers = new DbUsers(RegisterActivity.this);
                    long id = dbUsers.insertUser(name, email, password);

                    if (id > 0) {
                        Toast.makeText(RegisterActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                        limpiar();
                    } else {
                        Toast.makeText(RegisterActivity.this, "ERROR AL GUARDAR", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor, ingresa datos válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        // La contraseña debe tener al menos 6 caracteres, al menos una letra, al menos un dígito.
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
        return password.matches(passwordRegex);
    }


}