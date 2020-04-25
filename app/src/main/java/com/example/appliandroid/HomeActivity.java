package com.example.appliandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        View zone_a_button = findViewById(R.id.button_zone_a);
        View zone_b_button = findViewById(R.id.button_zone_b);
        zone_a_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInfoGroupActivity();
            }
        });
        zone_b_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInfoShelfActivity();
            }
        });
    }

    private void goToInfoShelfActivity() {
        Intent intent = new Intent(this,ShelfActivity.class);
        startActivity(intent);
    }

    private void goToInfoGroupActivity() {
        Intent intent = new Intent(this,GroupInfoActivity.class);
        startActivity(intent);
    }

}
