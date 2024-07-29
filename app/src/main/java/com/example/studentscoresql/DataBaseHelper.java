package com.example.studentscoresql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    static final String dataBaseName = "StudentGrades";
    static final int dataBaseVersion = 1;

    static final String dataBaseTable = "Gradebook";
    static final String name = "StudentName";
    static final String score = "Score";
    static final String teacher = "Teacher";

    static final String dataBaseTable2 = "Classroom";
    static final String teacher2 = "Teacher";
    static final String students = "Students";
    static final String teacherID = "Teacher ID";

    private static final String createQuery = "CREATE TABLE " + dataBaseTable +
            " ( " + name + " TEXT PRIMARY KEY, " + score + " TEXT NOT NULL, " + teacher + " TEXT)";
    private static final String createQuery2 = "CREATE TABLE " + dataBaseTable2 +
            " ( " + teacher2 + " TEXT PRIMARY KEY, " + students + " TEXT NOT NULL, " + teacherID + " TEXT)";

    public DataBaseHelper(Context context) {
        super(context, dataBaseName, null, dataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createQuery);
        sqLiteDatabase.execSQL(createQuery2);


    }
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("your_table_name", null, null);
        // Repeat the above line for each table in your database

        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dataBaseTable);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dataBaseTable2);

    }

}
