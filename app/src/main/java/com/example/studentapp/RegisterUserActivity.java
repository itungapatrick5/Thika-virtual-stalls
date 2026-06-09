package com.example.studentapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterUserActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextInputEditText username, password;
    MaterialButton register;
    TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        db = new DatabaseHelper(this);
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        register = findViewById(R.id.btnRegisterUser);
        loginLink = findViewById(R.id.tvBackToLogin);

        register.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean insert = db.addUser(user, pass);
                if (insert) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginLink.setOnClickListener(v -> finish());
    }
}
