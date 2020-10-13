package com.example.moneygement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class InputSavingAmountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_saving_amount);

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView dateOutput= (TextView)findViewById(R.id.date);
        Button tocontinuebutton = (Button)findViewById(R.id.tocontinue);
        Button cancelbutton = (Button)findViewById(R.id.cancel);

        //日付の受け取り
        Intent rIntent = getIntent();
        String date = rIntent.getStringExtra("checkday");
        dateOutput.setText(date);



        //キャンセルボタン
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(InputSavingAmountActivity.this, DispCalendarActivity.class);
                startActivity(intent2);
            }
        });

        //続けて記入ボタン
        tocontinuebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(InputSavingAmountActivity.this, DispCalendarActivity.class);
                startActivity(intent3);
            }
        });
    }
}