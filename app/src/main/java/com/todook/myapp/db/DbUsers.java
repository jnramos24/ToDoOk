package com.todook.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbUsers extends DbHelper {
    Context context;

    public DbUsers(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertUser(String Name, String Email, String Password) {
        long id = -1;

        // Verifica si el correo electrónico ya existe en la tabla UserAuthentication
        if (!isEmailExists(Email)) {
            try {
                DbHelper dbHelper = new DbHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("Email", Email);
                values.put("Password", Password);
                values.put("Role", "user"); // Establece el valor Role en User por defecto

                // Inserta el nuevo usuario en UserAuthentication
                long authId = db.insert(TABLE_AUTHENTICATION, null, values);

                if (authId > 0) {
                    // Si la inserción en UserAuthentication fue exitosa, insertamos en UserProfile
                    values.clear();
                    values.put("UserID", authId); // Establece el ID del nuevo usuario autenticado como clave foranea de UserProfile
                    values.put("Name", Name);
                    values.put("Email", Email);

                    // Insertamos el nuevo usuario en UserProfile
                    id = db.insert(TABLE_USERS, null, values);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Toast.makeText(context, "El correo electrónico ya está registrado.", Toast.LENGTH_SHORT).show();
        }

        return id;
    }

    public boolean editUser(int id, String Name, String Email, String Password) {

        boolean correct;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_USERS + " SET Name = '" + Name + "', Email = '" + Email + "', Password = '" + Password + "' WHERE Id=" + id);
            correct = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            correct = false;
        } finally {
            db.close();
        }

        return correct;
    }

    private boolean isEmailExists(String email) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_AUTHENTICATION +" WHERE Email=?", new String[]{email});
        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }

    public boolean checkCredentials(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_AUTHENTICATION +
                " WHERE Email=? AND Password=?", new String[]{email, password});

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }
}