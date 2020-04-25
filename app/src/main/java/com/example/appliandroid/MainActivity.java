package com.example.appliandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View logo = findViewById(R.id.logo_epsi);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomeActivity();
            }
        });
    }

    public void goToHomeActivity() {
        Intent intenet = new Intent(this,HomeActivity.class);
        startActivity(intenet);
    }
}