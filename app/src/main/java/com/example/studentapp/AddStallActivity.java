package com.example.studentapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddStallActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextInputEditText etVendor, etStall, etCat, etDesc, etPhone;
    MaterialButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stall);

        db = new DatabaseHelper(this);
        
        etVendor = findViewById(R.id.etVendorName);
        etStall = findViewById(R.id.etStallName);
        etCat = findViewById(R.id.etCategory);
        etDesc = findViewById(R.id.etDescription);
        etPhone = findViewById(R.id.etPhone);
        btnSubmit = findViewById(R.id.btnSubmitStall);

        btnSubmit.setOnClickListener(v -> {
            String vendor = etVendor.getText().toString();
            String stall = etStall.getText().toString();
            String cat = etCat.getText().toString();
            String desc = etDesc.getText().toString();
            String phone = etPhone.getText().toString();

            if (vendor.isEmpty() || stall.isEmpty() || cat.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean success = db.addStall(vendor, stall, cat, desc, phone);
                if (success) {
                    Toast.makeText(this, "Stall Published Successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Error publishing stall", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
