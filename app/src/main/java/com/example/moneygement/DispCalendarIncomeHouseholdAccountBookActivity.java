package com.example.moneygement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.moneygement.controller.MainActivity;

import java.util.Calendar;

public class DispCalendarIncomeHouseholdAccountBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_calendar_income_household_account_book);
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
                Integer incomeYear = year;
                Integer incomeMonth = month+1;
                Integer incomeDay = dayOfMonth;

                //インテント生成
                Intent intent = new Intent
                        (DispCalendarIncomeHouseholdAccountBookActivity.this,InputIncomeHouseholdAccountBookActivity.class);
                //年月日を受け渡す
                intent.putExtra("incomeYear",incomeYear);
                intent.putExtra("incomeMonth",incomeMonth);
                intent.putExtra("incomeDay",incomeDay);

                startActivity(intent);
            }
        };


}
