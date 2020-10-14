package com.example.moneygement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //現在の貯金額を取得
        Intent rIntent = getIntent();
        Integer saving = rIntent.getIntExtra("");
        dateOutput.setText();
        //↑現在の貯金額が入ります


        //目標貯金額を取得
        Integer targetsaving = rIntent.getIntExtra("");
        dateOutput.setTexr();
        //↑目標貯金額が入ります
    }


}
