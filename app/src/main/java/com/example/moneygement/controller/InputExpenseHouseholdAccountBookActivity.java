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

public class InputExpenseHouseholdAccountBookActivity extends AppCompatActivity{

    private String expenseYear;
    private String expenseMonth;
    private String expenseDay;
    private String expenseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_household_account_book);

        TextView date = (TextView)findViewById(R.id.date);
        //Intentのオブジェクトを生成する
        Intent rIntent  = getIntent();
        //年月日を受け取る
        expenseYear = String.valueOf(rIntent.getIntExtra("expenseYear",0));
        expenseMonth = String.valueOf(rIntent.getIntExtra("expenseMonth",0));
        expenseDay = String.valueOf(rIntent.getIntExtra("expenseDay",0));

        expenseDate = expenseYear+"年"+expenseMonth +"月"+expenseDay+ "日";

        date.setText(expenseDate);

    }

    @Override
    protected void onResume(){
        super.onResume();

        Button continueButton = (Button)findViewById(R.id.tocontinue);
        Button saveBtn = (Button)findViewById(R.id.savebutton);
        Button cancelBtn = (Button)findViewById(R.id.cancel);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //エディットテキストを探す
                EditText Amountofmoney = (EditText) findViewById(R.id.editTextNumberSigned);
                EditText textPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
                Spinner textcategory = (Spinner) findViewById(R.id.spinnerCategory);

                String txtmoney = Amountofmoney.getText().toString();
                String personName = textPersonName.getText().toString();
                String category = textcategory.getSelectedItem().toString();

//              保存処理

                Intent intent = new Intent(InputExpenseHouseholdAccountBookActivity.this, InputExpenseHouseholdAccountBookActivity.class);
                int money = Integer.parseInt("txtmoney");
                intent.putExtra("money", money);
                intent.putExtra("personName", personName);
                intent.putExtra("cotegory", category);
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

                Intent intent = new Intent(InputExpenseHouseholdAccountBookActivity.this, DispLedgerActivity.class);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputExpenseHouseholdAccountBookActivity.this, DispCalenderExpensesLedgerActivity.class);
                startActivity(intent);
            }
        });
    }
}


