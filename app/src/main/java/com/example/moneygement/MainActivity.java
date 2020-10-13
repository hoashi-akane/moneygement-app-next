package com.example.moneygement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickImageChange(View v){
        ImageView iv = (ImageView)findViewById(R.id.imageArea);
        Integer cyo = 0;
        Integer targetcyo = 50000;

        if(cyo >= targetcyo/5*4){

        }
    }
}
