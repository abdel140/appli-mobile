package com.example.appliandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseAppliActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView go_back_img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void showgoBackButton(){
        go_back_img = findViewById(R.id.go_back_img);
        if(go_back_img!=null) {
            go_back_img.setVisibility(View.VISIBLE);
            go_back_img.setOnClickListener(this);
        }
    }

    protected void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_back_img : finish();
                break;

        }

    }
}
