package com.example.studentscoresql; // Replace with your package name

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentscoresql.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private EditText name,score,teacher;
    private TextView ok;
    private ListView lvNames,lvTeachers,lvScores;
    DatabaseManager dbManager;
    private ArrayList dataListName,dataListScore,dataListTeacher;
    private ArrayAdapter<String> nameAdapter,scoreAdapter,teacherAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure you have a corresponding XML layout file
        name = findViewById(R.id.txtName);
        score = findViewById(R.id.txtScore);
        teacher = findViewById(R.id.txtTeacher);
//        ok = findViewById(R.id.txtData);
        // Sample data to populate the ListView
        String[] data = {"Item 1", "okkklala 2", "Item 3", "Item 4", "Item 5"};

        // Create an ArrayAdapter to populate the ListView
        dataListName = new ArrayList<>();
        nameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataListName);

        dataListScore = new ArrayList<>();
        scoreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataListScore);
        dataListTeacher = new ArrayList<>();
        teacherAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataListTeacher);





        // Get a reference to the ListView from the layout XML
        lvNames = findViewById(R.id.lvNames);

        // Set the adapter to the ListView
        lvNames.setAdapter(nameAdapter);
        
        lvScores = findViewById(R.id.lvScore);
        lvTeachers = findViewById(R.id.lvTeacher);
        lvScores.setAdapter(scoreAdapter);
        lvTeachers.setAdapter(teacherAdapter);
        dbManager = new DatabaseManager(this);
        try{
            dbManager.open();
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        dbManager.clearDatabase();

//        dbManager.clearTable("Gradebook");
//        dbManager.close();
    }

    public void btnInsertPressed(View v){
        dbManager.insert(name.getText().toString(),score.getText().toString(),teacher.getText().toString());
        name.setText(" ");
        score.setText(" ");
        teacher.setText(" ");

    }

    public void btnFetchPressed(View v){

        dataListName.clear();
        nameAdapter.notifyDataSetChanged();
        dataListScore.clear();
        scoreAdapter.notifyDataSetChanged();
        dataListTeacher.clear();
        teacherAdapter.notifyDataSetChanged();
        Cursor cursor = dbManager.fetch();
        if(cursor.moveToFirst()){
            do{

                @SuppressLint("Range") String score = cursor.getString(cursor.getColumnIndex(DataBaseHelper.score));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.name));
                dataListName.add(name);
                nameAdapter.notifyDataSetChanged();
                @SuppressLint("Range") String teacher = cursor.getString(cursor.getColumnIndex(DataBaseHelper.teacher));
                dataListScore.add(score);
                scoreAdapter.notifyDataSetChanged();
                dataListTeacher.add(teacher);
                teacherAdapter.notifyDataSetChanged();
//                lvNames.
//                ok.setText("Name: "+name+" score: "+score+" Teacher: "+teacher);
            }while (cursor.moveToNext());

        }

    }

    public void btnUpdatePressed(View v){

    }

    public void btnDeletePressed(View v){

    }
    public void btnNextPressed(){

    }


}
