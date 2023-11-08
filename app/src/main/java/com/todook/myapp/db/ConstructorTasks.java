package com.todook.myapp.db;

import android.content.ContentValues;
import android.content.Context;

import com.todook.myapp.modelo.Task;

import java.util.ArrayList;

public class ConstructorTasks {

    private Context context;
    public ConstructorTasks(Context context){
        this.context=context;
    }
    public ArrayList<Task> obtenerDatos(){

        DbHelper db = new DbHelper(context);
        return  db.obtenerTodasLasTareas();

    }

}
