package com.example.appliandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GroupInfoActivity extends BaseAppliActivity {

    public final String ACTIVITY_NAME = "Infos";
    public Student student1 = new Student("EL OUARDI","Abdelilah","abdelilah.elourdi@epsi.fr","DEV01","avatar1");
    public Student student2 = new Student("Helary","Lea","helary.lea@epsi.fr","DEV01","avatar2");
    public Student student3 = new Student("Kapaty","Laurence","kapaty.laurence@epsi.fr","DEV02","avatar3");
    public  Student[] students = new Student[]{student1,student2,student3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_info);
        TextView tv =findViewById(R.id.textNomAppli);
        tv.setText(ACTIVITY_NAME);
        showgoBackButton();
        Button btn_1 = findViewById(R.id.button_student_1);
        Button btn_2 = findViewById(R.id.button_student_2);
        Button btn_3 = findViewById(R.id.button_student_3);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_student_1:
                StudentInfoActivity.displayStudent(v.getContext(),students[0]);
                break;
            case R.id.button_student_2 :
                StudentInfoActivity.displayStudent(v.getContext(),students[1]);
                break;
            case R.id.button_student_3 :
                StudentInfoActivity.displayStudent(v.getContext(),students[2]);
                break;
        }
    }
}
