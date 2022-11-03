package com.alifandroidapp.studentregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "student.db";
    public final static String TABLE_NAME = "student_table";
    public final static String Id = "ID";
    public final static String Name= "NAME";
    public final static String Email= "EMAIL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+ "(ID TEXT PRIMARY KEY, NAME TEXT,EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Id, id);
        cv.put(Name, name);
        cv.put(Email, email);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+ " WHERE ID='"+id+"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public boolean updateData(String id, String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Id, id);
        cv.put(Name, name);
        cv.put(Email, email);
        db.update(TABLE_NAME, cv,"ID=?", new String[]{id});
        return true;
    }
    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "ID=?",new String[]{id});
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cursor;
    }

}
