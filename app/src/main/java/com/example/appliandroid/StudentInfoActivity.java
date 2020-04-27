package com.example.appliandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

public class StudentInfoActivity extends BaseAppliActivity {


    public String AVATAR_STANDARD = "drawable/logo.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);
        showgoBackButton();
        ImageView avatar_student = findViewById(R.id.avatar);
        TextView student_name = findViewById(R.id.student_name);
        TextView student_email = findViewById(R.id.student_email);
        TextView group_name = findViewById(R.id.group_name);
        Student student = (Student) getIntent().getExtras().get("student");
        TextView tv =findViewById(R.id.textNomAppli);
        tv.setText(student.getNom());
        String full_name =student.getNom() + " " +  student.getPrenom();
        student_name.setText(full_name);
        student_email.setText(student.getEmail());
        group_name.setText(student.getGroup());
        //String img_src = "drawable/"+student.getAvatar()+".jpg";
        //avatar_student.setImageURI(Uri.parse(img_src));
        avatar_student.setImageResource(R.drawable.avatar1);
    }


    public static void displayStudent(Context context, Student student){
        Intent intent = new Intent(context, StudentInfoActivity.class);
        intent.putExtra("student",student);
        context.startActivity(intent);
    }
}
