package com.todook.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    ImageView imgback;
    Button btnActualize;

    Button btncamara;
    ImageView imgfoto;

    String rutaImagen;

    TextView nombre, email, contraseña;

    ListView lv_datosuser;
    DbHelper conn;
    List<String> item =null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        lv_datosuser = findViewById(R.id.lv_datosuser);
        showUser();
        conn = new DbHelper(getApplicationContext(), "toDoOk.db", null, 3);



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
                Intent intent = new Intent(Profile.this, SetProfileActivity.class);
                startActivity(intent);

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

    private void showUser() {
        conn = new DbHelper(this);
        Cursor c = conn.getUser();
        item = new ArrayList<String>();
        String Name = "", Email="";

        if(c.moveToFirst()){
            do {
                Name = c.getString(1);
                Email = c.getString(2);
                item.add(Name+""+Email);

            }while(c.moveToNext());
        }

        ArrayAdapter<String> adaptor =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, item);
        lv_datosuser.setAdapter(adaptor);
    }

}
