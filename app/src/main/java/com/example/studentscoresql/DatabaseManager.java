package com.example.studentscoresql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DataBaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx){
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;
    }
    public void clearTable(String tableName) {
        database.execSQL("DELETE FROM " + tableName);
    }


    public void close(){
        dbHelper.close();
    }

    public void insert(String name, String score,String teacher){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.name,name);
        contentValues.put(DataBaseHelper.score,score);
        contentValues.put(DataBaseHelper.teacher,teacher);
        database.insert(DataBaseHelper.dataBaseTable,null,contentValues);

    }
    public void insert2(String teacher, String students,String teacherID){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.teacher2,teacher);
        contentValues.put(DataBaseHelper.students,students);
        contentValues.put(DataBaseHelper.teacherID,teacherID);
        database.insert(DataBaseHelper.dataBaseTable2,null,contentValues);

    }




    public Cursor fetch(){
        String [] columns = new String[]{DataBaseHelper.name,DataBaseHelper.score,DataBaseHelper.teacher};
        Cursor cursor = database.query(DataBaseHelper.dataBaseTable,columns,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }


        return cursor;
    }

    public Cursor fetch2(){
        String [] columns = new String[]{DataBaseHelper.teacher2,DataBaseHelper.students,DataBaseHelper.teacherID};
        Cursor cursor = database.query(DataBaseHelper.dataBaseTable2,columns,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }


        return cursor;
    }


    public int update(long score,String name,String teacher){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.name,name);
        contentValues.put(DataBaseHelper.score,score);
        contentValues.put(DataBaseHelper.teacher,teacher);
        int ret = database.update(DataBaseHelper.dataBaseTable,contentValues,DataBaseHelper.score+"="+score,null);
        return ret;
    }

}
