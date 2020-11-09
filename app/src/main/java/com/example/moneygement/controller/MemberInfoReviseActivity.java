package com.example.moneygement.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.moneygement.R;

public class MemberInfoReviseActivity extends AppCompatActivity {
    private ImageView Imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info_revise);

        Imageview = findViewById(R.id.edit_image2);
        Imageview = findViewById(R.id.icon_image2);
        Imageview = findViewById(R.id.imageView6);
    }
}