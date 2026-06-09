package com.example.studentapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterStudentActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText name, email, course;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        db = new DatabaseHelper(this);
        name = findViewById(R.id.student_name);
        email = findViewById(R.id.student_email);
        course = findViewById(R.id.student_course);
        save = findViewById(R.id.btnSaveStudent);

        save.setOnClickListener(v -> {
            String sName = name.getText().toString();
            String sEmail = email.getText().toString();
            String sCourse = course.getText().toString();

            if (sName.isEmpty() || sEmail.isEmpty() || sCourse.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean insert = db.addStudent(sName, sEmail, sCourse);
                if (insert) {
                    Toast.makeText(this, "Student registered successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
