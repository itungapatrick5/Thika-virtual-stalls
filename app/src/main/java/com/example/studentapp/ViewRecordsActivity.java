package com.example.studentapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ViewRecordsActivity extends AppCompatActivity {
    DatabaseHelper db;
    ListView listView;
    ArrayList<String> studentList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.studentsList);
        studentList = new ArrayList<>();

        viewData();
    }

    private void viewData() {
        Cursor cursor = db.getAllStudents();
        if (cursor.getCount() == 0) {
            studentList.add("No records found");
        } else {
            while (cursor.moveToNext()) {
                studentList.add(cursor.getString(1) + " - " + cursor.getString(3)); // Name - Course
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listView.setAdapter(adapter);
    }
}
