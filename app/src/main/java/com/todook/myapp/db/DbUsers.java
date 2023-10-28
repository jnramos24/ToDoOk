package com.todook.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsers extends DbHelper {
    Context context;

    public DbUsers(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertUser(String Name, String Email, String Password) {

        long id = 0;

        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", Name);
        values.put("email", Email);
        values.put("password", Password);

        id = db.insert(TABLE_USERS, null, values);
        } catch(Exception ex){
            ex.toString();
        }
        return  id;
    }
    public boolean editUser(int id, String Name, String Email, String Password) {

        boolean correct = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_USERS + " SET nombre = '" + Name + "', email = '" + Email + "', password = '" + Password + "' WHERE id='" + id + "' ");
            correct = true;
        } catch (Exception ex) {
            ex.toString();
            correct = false;
        } finally {
            db.close();
        }

        return correct;
    }
}
