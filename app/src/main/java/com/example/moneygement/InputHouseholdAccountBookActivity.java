package com.example.moneygement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moneygement.controller.MainActivity;

import org.w3c.dom.Text;

public class InputHouseholdAccountBookActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_household_account_book);

        TextView tvOutput = (TextView)findViewById(R.id.date);
        //Intentのオブジェクトを生成する
        Intent rIntent  = getIntent();
        //年月日を受け取る
        String expenseYear = String.valueOf(rIntent.getIntExtra("expenseYear",0));
        String expenseMonth = String.valueOf(rIntent.getIntExtra("expenseMonth",0));
        String expenseDay = String.valueOf(rIntent.getIntExtra("expenseDay",0));

        String incomeDate = expenseYear+"年"+expenseMonth +"月"+expenseDay+ "日";

    }

    @Override
    protected void onResume(){
        super.onResume();
        //エディットテキストを探す
        EditText Amountofmoney = (EditText)findViewById(R.id.editTextNumberSigned);
        EditText textPersonName = (EditText)findViewById(R.id.editTextTextPersonName);
        Spinner textcategory = (Spinner) findViewById(R.id.spinnerCategory);
        //インテント生成
        Intent intent  = new Intent(InputHouseholdAccountBookActivity.this, MainActivity.class);

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

        Button continueButton = (Button)findViewById(R.id.tocontinue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputHouseholdAccountBookActivity.this,InputHouseholdAccountBookActivity.class);
            }
        });

    }
}


