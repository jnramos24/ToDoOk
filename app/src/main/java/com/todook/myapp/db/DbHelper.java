package com.todook.myapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "toDoOk.db";
    public static final String TABLE_USERS = "UserProfile";
    public static final String TABLE_AUTHENTICATION = "UserAuthentication";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tabla UserProfile
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USERS + "(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Email TEXT NOT NULL," +
                "Password TEXT UNIQUE NOT NULL)" );
        //Tabla UserAuthentication
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_AUTHENTICATION + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Email TEXT UNIQUE," +
                "Password TEXT," +
                "Role TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Eliminar las tablas existentes y volver a crearlas
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHENTICATION);
        onCreate(sqLiteDatabase);
    }
}
