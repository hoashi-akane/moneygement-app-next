package com.example.moneygement.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneygement.R;
import com.example.moneygement.service.AuthService;

public class NewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //保持されてる会員IDと名前をとってくる処理
        AuthService authService = new AuthService();
        setContentView(R.layout.activity_newgroup);
        SharedPreferences sharedPreferences = authService.createAuthSharedPreferences(getApplicationContext());
        int id = sharedPreferences.getInt("id",0 );

        Intent intent = new Intent(NewGroupActivity.this,NewGroupActivity.class);
        intent.putExtra("userId",id);

        startActivity(intent);
    }
    

    @Override
    protected void onResume() {
        super.onResume();
        Button button =  (Button)findViewById(R.id.inputGroupbtn);

        //会員IDを取得
        Intent rIntent = getIntent();
        int userId= rIntent.getIntExtra("userId",0);

        //作成ボタンが押されたときの処理
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditTextを探す
                EditText editGroupName = (EditText)findViewById(R.id.groupName);
                //インテント生成
                Intent intent = new Intent(NewGroupActivity.this,MainActivity.class);
                //EditTextから文字を取得
                String groupName = editGroupName.getText().toString();

                /*ここでグループテーブルにグループIDを、
                家計簿テーブルに会員ID・グループID・家計簿名・家計簿作成日
                を追加する処理*/


            }
        });

        Button cancelButton = (Button)findViewById(R.id.cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(NewGroupActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });
    }
}
