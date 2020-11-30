package com.example.moneygement.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moneygement.R;

public class InputIncomeHouseholdAccountBookActivity  extends AppCompatActivity{

    private String incomeYear;
    private String incomeMonth;
    private String incomeDay;
    private String incomeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_income_household_account_book);

        TextView date = (TextView)findViewById(R.id.date);
        //Intentのオブジェクトを生成する
        Intent rIntent  = getIntent();
        //年月日を受け取る
        incomeYear = String.valueOf(rIntent.getIntExtra("incomeYear",0));
        incomeMonth = String.valueOf(rIntent.getIntExtra("incomeMonth",0));
        incomeDay = String.valueOf(rIntent.getIntExtra("incomeDay",0));

        incomeDate = incomeYear+"年"+incomeMonth +"月"+incomeDay+ "日";

        date.setText(incomeDate);

    }

    @Override
    protected void onResume(){
        super.onResume();
        //エディットテキストを探す

        Button continueButton = (Button)findViewById(R.id.tocontinue);
        Button saveBtn = (Button)findViewById(R.id.savebutton);
        Button cancelBtn = (Button)findViewById(R.id.cancel);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              値を受取り
                EditText amounToMoney = (EditText)findViewById(R.id.editTextNumberSigned);
                EditText textPersonName = (EditText)findViewById(R.id.editTextTextPersonName);
                Spinner textCategory = (Spinner) findViewById(R.id.spinnerCategory);

                String txtmoney = amounToMoney.getText().toString();
                String personName = textPersonName.getText().toString();
                String category = textCategory.getSelectedItem().toString();

//              保存処理

                Intent intent = new Intent(InputIncomeHouseholdAccountBookActivity.this,InputIncomeHouseholdAccountBookActivity.class);
//              同じ日付をセット（続けて入力する場合日付が必要
                int money = Integer.parseInt("txtmoney");
                intent.putExtra("incomeYear", incomeYear);
                intent.putExtra("incomeMonth",incomeMonth);
                intent.putExtra("incomeDay",incomeDay);
                startActivity(intent);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText amounToMoney = (EditText)findViewById(R.id.editTextNumberSigned);
                EditText textPersonName = (EditText)findViewById(R.id.editTextTextPersonName);
                Spinner textCategory = (Spinner) findViewById(R.id.spinnerCategory);

                String txtmoney = amounToMoney.getText().toString();
                String personName = textPersonName.getText().toString();
                String category = textCategory.getSelectedItem().toString();

//              保存処理

                Intent intent = new Intent(InputIncomeHouseholdAccountBookActivity.this, DispLedgerActivity.class);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputIncomeHouseholdAccountBookActivity.this, DispCalendarIncomeLedgerActivity.class);
                startActivity(intent);
            }
        });

    }
}


