package com.example.moneygement.controller;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneygement.R;

public class MemberInfoActivity extends AppCompatActivity {
    private ImageView Imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmember);

        Imageview = findViewById(R.id.icon_image);
        Imageview = findViewById(R.id.edit_image);
    }
}