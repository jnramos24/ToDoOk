package com.todook.myapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "toDoOk.db";
    public static final String TABLE_USERS = "UserProfile";
    public static final String TABLE_AUTHENTICATION = "UserAuthentication";

    public static final String TABLE_USERS_NAME   = "name";
    public static final String TABLE_USERS_EMAIL     = "email";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Tabla UserAuthentication
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_AUTHENTICATION + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Email TEXT UNIQUE," +
                "Password TEXT," +
                "Role TEXT)");
        //Tabla UserProfile
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USERS + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserID INTEGER," +
                "Name TEXT NOT NULL," +
                "Email TEXT NOT NULL," +
                "FOREIGN KEY(UserID) REFERENCES " + TABLE_AUTHENTICATION + "(ID))");

        //Tabla Tareas
        String queryCrearTablaTareas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_TASKS + "(" +
                ConstantesBaseDatos.TABLE_TASKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_TASKS_NAME + " TEXT NOT NULL, " +
                ConstantesBaseDatos.TABLE_TASKS_TASKDATE + " TEXT NOT NULL, " +
                ConstantesBaseDatos.TABLE_TASKS_TIMEDATE + " TEXT NOT NULL, " +
                ConstantesBaseDatos.TABLE_TASKS_TYPE + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + "Id" + ") " +
                "REFERENCES " + TABLE_USERS + "(" + "Id" + ")" +
                ")";
        sqLiteDatabase.execSQL(queryCrearTablaTareas);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Eliminar las tablas existentes y volver a crearlas
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHENTICATION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_TASKS);
        onCreate(sqLiteDatabase);
    }

    public Cursor getUser(){
        String columnas [] = { TABLE_USERS_NAME,TABLE_USERS_EMAIL};
        Cursor c =this.getReadableDatabase().query(TABLE_USERS, columnas, null, null, null, null, null);
        return c;
    }


}
