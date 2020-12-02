package com.example.moneygement.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneygement.R;
import com.example.moneygement.service.AuthService;

public class NewAdviserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //保持されてる会員IDと名前をとってくる処理
        AuthService authService = new AuthService();
        setContentView(R.layout.activity_newadviser);
        SharedPreferences sharedPreferences = authService.createAuthSharedPreferences(getApplicationContext());
        int id = sharedPreferences.getInt("id",0 );
        String name = sharedPreferences.getString("name", "");

        Intent intent = new Intent(NewAdviserActivity.this,NewAdviserActivity.class);
          intent.putExtra("userId",id);
          intent.putExtra("userName",name);

          startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();


        //名前がすでに登録されていたら入力フォームに表示する処理
        Intent rIntent = getIntent();
        int userId = rIntent.getIntExtra("userId", 0);
        String userName = rIntent.getStringExtra("userName");
        if(!userName.equals("")) {
            TextView tvOutput = (TextView) findViewById(R.id.name);
            //入力フォームに表示
            tvOutput.setText(userName);
        }

        //アドバイザ登録ボタンが押されたときの処理
        Button button = (Button)findViewById(R.id.inputAdviserbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditTextを探す
                EditText editName = (EditText)findViewById(R.id.name);
                EditText editAdviserName = (EditText)findViewById(R.id.abviserNickName);
                EditText editSelfIntroduction = (EditText) findViewById(R.id.selfIntroduction);

                //インテント生成
                Intent intent = new Intent(NewAdviserActivity.this,MainActivity.class);

                //EditTextの文字を取得する
                String name = editName.getText().toString();
                String adviserName = editAdviserName.getText().toString();
                String selfIntroduction = editSelfIntroduction.getText().toString();
                if(!name.equals("") && !adviserName.equals("")  && !selfIntroduction.equals("")){
                    //なんかここでデータベースに名前とアドバイザニックネームと自己紹介を入れる処理したい



                }
                startActivity(intent);
            }
        });

        Button cancelButton = (Button)findViewById(R.id.cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(NewAdviserActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });

    }
}
