package com.example.appliandroid;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseAppliActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView go_back_img;
    Application app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = getApplication();
    }
    public void showgoBackButton(){
        go_back_img = findViewById(R.id.go_back_img);
        if(go_back_img!=null) {
            go_back_img.setVisibility(View.VISIBLE);
            go_back_img.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_back_img : finish();
                break;

        }

    }
}
