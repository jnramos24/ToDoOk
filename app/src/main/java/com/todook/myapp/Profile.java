package com.todook.myapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.todook.myapp.db.DbHelper;

import java.io.File;
import java.io.IOException;

public class Profile extends AppCompatActivity {

    ImageView imgback;
    Button btnActualize;

    Button btncamara;
    ImageView imgfoto;

    String rutaImagen;

    TextView nombre, email, contraseña;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btncamara = findViewById(R.id.btncamara);
        imgfoto = findViewById(R.id.imgfoto);

        btncamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }

        });

        imgback = findViewById(R.id.icon_volver_R);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        nombre = findViewById(R.id.Nombre);
        email = findViewById(R.id.Email);
        contraseña = findViewById(R.id.Contraseña);

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

    ActivityResultLauncher<Intent>camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK) {
                Bundle extras = result.getData().getExtras();
                Bitmap imgBitmap = (Bitmap) extras.get("data");
                imgfoto.setImageBitmap(imgBitmap);
            }

        }
    });

    private File crearImagen() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpj", directorio);

        rutaImagen = imagen.getAbsolutePath();
        return imagen;

    }

}
