package com.example.moneygement.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.moneygement.R;

public class ChatActivity extends AppCompatActivity {
    private EditText editText;
    private ImageView ImageView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editText = findViewById(R.id.edit_text);

        ImageView = findViewById(R.id.activity_main);
        ImageView = findViewById(R.id.activity_second);
        ImageView = findViewById(R.id.activity_third);
    }
}