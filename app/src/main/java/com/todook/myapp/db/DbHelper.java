package com.todook.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.todook.myapp.modelo.Task;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
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


    public ArrayList<Task> obtenerTodasLasTareas() {
        ArrayList<Task> tasks = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_TASKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Task contactoActual = new Task();
            contactoActual.setId(registros.getInt(0));
            contactoActual.setTaskname(registros.getString(1));
            contactoActual.setTaskdate(registros.getString(2));
            contactoActual.setTimedate(registros.getString(3));
            contactoActual.setType(registros.getInt(4));


           tasks.add(contactoActual);
        }
        // Cerrar el Cursor después de usarlo
        registros.close();
        db.close();
        return tasks;
    }

    public void eliminarTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantesBaseDatos.TABLE_TASKS, ConstantesBaseDatos.TABLE_TASKS_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void actualizarTarea(int taskId, String editedTitle, String editedFecha, String editedTime, int editedPriority) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ConstantesBaseDatos.TABLE_TASKS_NAME, editedTitle);
        values.put(ConstantesBaseDatos.TABLE_TASKS_TASKDATE, editedFecha);
        values.put(ConstantesBaseDatos.TABLE_TASKS_TIMEDATE, editedTime);
        values.put(ConstantesBaseDatos.TABLE_TASKS_TYPE, editedPriority);


        // Realizar la actualización
        db.update(ConstantesBaseDatos.TABLE_TASKS, values, ConstantesBaseDatos.TABLE_TASKS_ID + " = ?",
                new String[]{String.valueOf(taskId)});


        // Cerrar la conexión a la base de datos
        db.close();
    }



}
