package com.example.moneygement.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.moneygement.R;

public class DispCalenderExpensesLedgerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_calender_expenses_ledger);
        //CalenderViewにリスナーを設定
        ((CalendarView) findViewById(R.id.calendarview)).setOnDateChangeListener(listener);
    }

    //カレンダーの日付部分タップ時のリスナー
    CalendarView.OnDateChangeListener listener = new CalendarView.OnDateChangeListener() {
        //view・・・押下されたカレンダーのインスタンス
        //year・・・タップされた日付の「年」
        //month・・・タップされた日付の「月」※月は０月から始まるから＋１する
        //dayOfMonth・・・タップされた日付の「日」
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){
            Integer expenseYear = year;
            Integer expenseMonth = month+1;
            Integer expenseDay = dayOfMonth;

            //インテント生成
            Intent intent = new Intent
                    (DispCalenderExpensesLedgerActivity.this,InputIncomeHouseholdAccountBookActivity.class);
            //年月日を受け渡す
            intent.putExtra("expenseYear",expenseYear);
            intent.putExtra("expenseMonth",expenseMonth);
            intent.putExtra("expenseDay",expenseDay);

            startActivity(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Button topButton = (Button)findViewById(R.id.topbutton);
        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispCalenderExpensesLedgerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}