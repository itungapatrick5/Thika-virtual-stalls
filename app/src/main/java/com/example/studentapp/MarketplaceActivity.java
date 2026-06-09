package com.example.studentapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MarketplaceActivity extends AppCompatActivity {
    DatabaseHelper db;
    ListView listView;
    ArrayList<StallModel> stallList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.stallListView);
        stallList = new ArrayList<>();

        loadStalls();
    }

    private void loadStalls() {
        Cursor cursor = db.getAllStalls();
        if (cursor.moveToFirst()) {
            do {
                stallList.add(new StallModel(
                        cursor.getString(1), // Vendor
                        cursor.getString(2), // Stall Name
                        cursor.getString(3), // Category
                        cursor.getString(4), // Description
                        cursor.getString(5)  // Phone
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();

        StallAdapter adapter = new StallAdapter();
        listView.setAdapter(adapter);
    }

    // Inner class for the model
    class StallModel {
        String vendor, name, cat, desc, phone;
        StallModel(String v, String n, String c, String d, String p) {
            this.vendor = v; this.name = n; this.cat = c; this.desc = d; this.phone = p;
        }
    }

    // Custom Adapter for Modern UI
    class StallAdapter extends BaseAdapter {
        @Override
        public int getCount() { return stallList.size(); }
        @Override
        public Object getItem(int position) { return stallList.get(position); }
        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(MarketplaceActivity.this).inflate(R.layout.item_stall, parent, false);
            }

            StallModel stall = stallList.get(position);
            TextView name = convertView.findViewById(R.id.txtStallName);
            TextView cat = convertView.findViewById(R.id.txtCategory);
            TextView vendor = convertView.findViewById(R.id.txtVendor);

            name.setText(stall.name);
            cat.setText(stall.cat);
            vendor.setText("By: " + stall.vendor);

            return convertView;
        }
    }
}
