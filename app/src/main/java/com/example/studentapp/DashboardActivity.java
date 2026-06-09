package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        MaterialCardView cardAdd = findViewById(R.id.cardAddStall);
        MaterialCardView cardMarket = findViewById(R.id.cardMarketplace);

        cardAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddStallActivity.class));
        });

        cardMarket.setOnClickListener(v -> {
            startActivity(new Intent(this, MarketplaceActivity.class));
        });
    }
}
