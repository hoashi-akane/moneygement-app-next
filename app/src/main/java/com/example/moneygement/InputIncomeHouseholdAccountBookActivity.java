package com.example.moneygement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moneygement.controller.MainActivity;

import org.w3c.dom.Text;

public class InputIncomeHouseholdAccountBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_income_household_account_book);

        TextView tvOutput = (TextView)findViewById(R.id.date);
        //Intentのオブジェクトを生成する
        Intent rIntent  = getIntent();
        //年月日を受け取る
        String incomeYear = String.valueOf(rIntent.getIntExtra("incomeYear",0));
        String incomeMonth = String.valueOf(rIntent.getIntExtra("incomeMonth",0));
        String incomeDay = String.valueOf(rIntent.getIntExtra("incomeDay",0));

        String incomeDate = incomeYear+"年"+incomeMonth +"月"+incomeDay+ "日";

    }

    @Override
    protected void onResume(){
        super.onResume();
        //エディットテキストを探す
        EditText Amountofmoney = (EditText)findViewById(R.id.editTextNumberSigned);
        EditText textPersonName = (EditText)findViewById(R.id.editTextTextPersonName);
        Spinner textcategory = (Spinner) findViewById(R.id.spinnerCategory);
        //インテント生成
        Intent intent  = new Intent(InputIncomeHouseholdAccountBookActivity.this, MainActivity,class);

        //エディットテキストの文字を取得する

        String txtmoney = Amountofmoney.getText().toString();
        String personName = textPersonName.getText().toString();
        String category = textcategory.getSelectedItem().toString();

        if(!txtmoney.equals("") && !personName.equals("") && !category.equals("")) {
            int money = Integer.parseInt("txtmoney");
            intent.putExtra("money", money);
            intent.putExtra("personName",personName);
            intent.putExtra("cotegory",category);
        }
        startActivity(intent);

    }
}


